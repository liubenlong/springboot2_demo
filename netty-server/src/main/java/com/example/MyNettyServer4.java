package com.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 自定义消息解码器
 */
public class MyNettyServer4 {
    public static void main(String[] args) {
        //配置服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)//配置主从线程组
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)//配置一些TCP的参数
                    .childHandler(new MyChildHandler4());//添加自定义的channel
            //绑定8080端口
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            //服务端监听端口关闭
            ChannelFuture future = channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //netty优雅停机
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

class MyChildHandler4 extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline()
                .addLast(new MyCustomMessageEncoder())
                .addLast(new MyCustomMessageDecoder())
                .addLast(new TimeServerHandler4());
    }
}

class MyCustomMessageDecoder extends ByteToMessageDecoder {
    // 消息头：发送端写的是一个int，占用4字节。
    private final static int HEAD_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //
        if (in.readableBytes() < HEAD_LENGTH) {
            return;
        }

        // 标记一下当前的readIndex的位置
        in.markReaderIndex();

        // 读取数据长度
        int dataLength = in.readInt();
        // 我们读到的消息体长度为0，这是不应该出现的情况，这里出现这情况，关闭连接。
        if (dataLength < 0) {
            System.out.println("ERROR!");
            ctx.close();
        }

        //读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex.
        // 这个配合markReaderIndex使用的。
        // 把readIndex重置到mark的地方
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        // 将缓冲区的数据读到字节数组
        byte[] body = new byte[dataLength];
        in.readBytes(body);
        //将byte数据转化为我们需要的对象。
        Object msg = convertToObj(body);
        out.add(msg);
    }

    private Object convertToObj(byte[] body) {
        return new String(body, 0, body.length);
    }
}

class MyCustomMessageEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        // 要发送的数据
        // 这里如果是自定义的类型，msg即为自定义的类型，需要转为byte[]
        byte[] body = ((ByteBuf) msg).array();

        // 数据长度
        int dataLength = body.length;
        // 缓冲区先写入数据长度
        out.writeInt(dataLength);
        // 再写入数据
        out.writeBytes(body);
    }
}


/**
 * TimeServerHandler这个才是服务端真正处理请求的服务方法
 */
class TimeServerHandler4 extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStr = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                format.format(new Date()) + "" : "BAD ORDER";

        //受到一次请求，count加1
        System.out.println("第 " + count++ + " 次收到客户端请求：" + body + "  返回响应：" + currentTimeStr);

        ByteBuf resp = Unpooled.copiedBuffer(currentTimeStr.getBytes());

        ctx.write(resp);//将消息发送到发送缓冲区
//        ctx.writeAndFlush(resp);//如果这里使用writeAndFlush，则下面channelReadComplete中就不需要flush了
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();//将消息从发送缓冲区中写入socketchannel中
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
