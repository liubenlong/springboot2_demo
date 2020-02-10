package com.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 第一个简单的 netty  http 服务器
 */
public class MyHttpServer {

    int port;

    public MyHttpServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer());

        ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
        System.out.println("http server started . port : " + port);
        f.channel().closeFuture().sync();

    }

    public static void main(String[] args) throws Exception {
        MyHttpServer server = new MyHttpServer(8080);// 8081为启动端口
        server.start();
    }

}

class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec())// http 编解器
                // http 消息聚合器  512*1024为接收的最大contentlength
                .addLast("httpAggregator", new HttpObjectAggregator(512 * 1024))
                // 支持异步发送大的码流(大的文件传输),但不占用过多的内存，防止java内存溢出
                .addLast("http-chunked", new ChunkedWriteHandler())
                .addLast(new HttpRequestHandler());// 请求处理器
    }
}


class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) {
        //100 Continue
//        if (HttpUtil.is100ContinueExpected(req)) {
//            ctx.write(new DefaultFullHttpResponse(
//                    HttpVersion.HTTP_1_1,
//                    HttpResponseStatus.CONTINUE));
//        }
        // 获取请求的uri
        String uri = req.uri();
        Map<String, String> resMap = new HashMap<>();
        resMap.put("method", req.method().name());
        resMap.put("uri", uri);
        String msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri + "</body></html>";
        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        // 将html write到客户端
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}