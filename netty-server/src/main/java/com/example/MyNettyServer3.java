package com.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 分隔符解码器解决拆包粘包问题
 */
public class MyNettyServer3 {
    public static void main(String[] args) {
        //配置服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)//配置主从线程组
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)//配置一些TCP的参数
                    .childHandler(new MyChildHandler3());//添加自定义的channel
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

class MyChildHandler3 extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ByteBuf delimiter = Unpooled.copiedBuffer("#".getBytes());//这里我们以 # 号分隔
        socketChannel.pipeline()
                .addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                .addLast(new StringDecoder())
                .addLast(new TimeServerHandler3());
    }
}

/**
 * TimeServerHandler这个才是服务端真正处理请求的服务方法
 */
class TimeServerHandler3 extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStr = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                format.format(new Date()) + "" : "BAD ORDER";

        //受到一次请求，count加1
        System.out.println("第 " + count++ + " 次收到客户端请求：" + body + "  返回响应：" + currentTimeStr);

        ByteBuf resp = Unpooled.copiedBuffer((currentTimeStr + "#").getBytes());
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

