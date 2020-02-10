package com.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模拟拆包粘包问题
 */
public class MyNettyServer1 {
    public static void main(String[] args) {
        //配置服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)//配置主从线程组
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)//配置一些TCP的参数
                    .childHandler(new MyChildHandler1());//添加自定义的channel
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

class MyChildHandler1 extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        //将自定义的channelhandler添加到channelpipeline的末尾
        socketChannel.pipeline().addLast(new TimeServerHandler1());
    }
}

/**
 * TimeServerHandler这个才是服务端真正处理请求的服务方法
 */
class TimeServerHandler1 extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //将请求入参转为ByteBuf对象
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        //由于我们这里传的参数是string，所以直接强制转换
        String body = new String(bytes, "UTF-8");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStr = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                format.format(new Date()) + "" : "BAD ORDER";

        //受到一次请求，count加1
        System.out.println("第 " + count++ + " 次收到客户端请求：" + body + "  返回响应：" + currentTimeStr);

        ByteBuf resp = Unpooled.copiedBuffer(currentTimeStr.getBytes());
        ctx.write(resp);//将消息发送到发送缓冲区
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

