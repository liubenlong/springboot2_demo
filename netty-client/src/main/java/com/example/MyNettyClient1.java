package com.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 模拟拆包粘包问题
 */
public class MyNettyClient1 {
    public static void main(String[] args) {
        //客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new TimeClientHandler1());
                        }
                    });

            //异步链接服务器
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8080).sync();
            //等等客户端链接关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //优雅停机
            group.shutdownGracefully();
        }
    }
}

//客户端业务逻辑处理类
class TimeClientHandler1 extends ChannelInboundHandlerAdapter {
    private int count = 0;

    /**
     * 客户端与服务器TCP链路链接成功后调用该方法
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        byte[] req = "QUERY TIME ORDER".getBytes();
        for (int i = 0; i < 100; i++) {
            ByteBuf firstMsg = Unpooled.buffer(req.length);
            firstMsg.writeBytes(req);
            ctx.writeAndFlush(firstMsg);//写入缓冲并且发送到socketchannel
        }
    }

    /**
     * 读取到服务端相应后执行该方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String body = new String(bytes, "UTF-8");
        System.out.println("第" + count++ + "次受到服务端返回：" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }

}


