package com.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * netty实现聊天室
 */
public class MyChatRoomServer {

    int port;

    public MyChatRoomServer(int port) {
        this.port = port;
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            bootstrap.group(boss, work)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatRoomServerInitializer());

            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            System.out.println("http server started . port : " + port);
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        MyChatRoomServer server = new MyChatRoomServer(8080);// 8081为启动端口
        server.start();
    }
}

class ChatRoomServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec())// http 编解器
                // http 消息聚合器  512*1024为接收的最大contentlength
                .addLast("httpAggregator", new HttpObjectAggregator(512 * 1024))
                // 支持异步发送大的码流(大的文件传输),但不占用过多的内存，防止java内存溢出
                .addLast("http-chunked", new ChunkedWriteHandler())
                .addLast(new IdleStateHandler(10, 10, 30))
                .addLast(new ChatRoomRequestHandler());// 请求处理器
    }
}

class ChatRoomRequestHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到消息：" + msg);
        if (msg instanceof FullHttpRequest) {
            //以http请求形式接入，但是走的是websocket
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            //处理websocket客户端的消息
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //添加连接
        System.out.println("客户端加入连接：" + ctx.channel());
        ChannelSupervise.addChannel(ctx.channel());

        TextWebSocketFrame tws = new TextWebSocketFrame(
                "欢迎 " + ctx.channel().id().asShortText() + "; 当前在线总人数：" + ChannelSupervise.count());
        ChannelSupervise.send2All(tws);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //断开连接
        System.out.println("客户端断开连接：" + ctx.channel());
        ChannelSupervise.removeChannel(ctx.channel());

        TextWebSocketFrame tws = new TextWebSocketFrame(
                "再见 " + ctx.channel().id().asShortText() + "; 当前在线总人数：" + ChannelSupervise.count());
        ChannelSupervise.send2All(tws);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /*
    对WebSocket请求进行处理
     */
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息，如果是，则构造pong消息返回。用于心跳检测
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            System.out.println("本例程仅支持文本消息，不支持二进制消息");
            throw new UnsupportedOperationException(
                    String.format("%s frame types not supported", frame.getClass().getName()));
        }

        //处理客户端请求并返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println(request);
        TextWebSocketFrame tws = new TextWebSocketFrame(ctx.channel().id().asShortText() + "：" + request);

        // 群发
        ChannelSupervise.send2All(tws);
    }

    /**
     * 唯一的一次http请求。
     * 该方法用于处理websocket握手请求
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        //如果HTTP解码失败，返回异常。要求Upgrade为websocket，过滤掉get/Post
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            //若不是websocket方式，则创建BAD_REQUEST（400）的req，返回给客户端
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

//        构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:8080/websocket", null, false);
        //通过工厂来创建WebSocketServerHandshaker实例
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            /*
            通过WebSocketServerHandshaker来构建握手响应消息返回给客户端。
            同时将WebSocket相关编解码类添加到ChannelPipeline中，该功能需要阅读handshake的源码。
             */
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 拒绝不合法的请求，并返回错误信息
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx,
                                         FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }

        ChannelFuture f = ctx.channel().writeAndFlush(res);

        // 如果是非Keep-Alive，关闭连接
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println("读空闲==");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                System.out.println("写空闲==");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                System.out.println("读写空闲==");
                ctx.channel().close();
            } else {
                System.out.println("error!!" + evt.getClass().toString());
            }
        } else {
            System.out.println(evt.getClass().toString());
        }

    }
}
