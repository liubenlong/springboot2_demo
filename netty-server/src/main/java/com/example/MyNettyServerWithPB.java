package com.example;

import com.example.pojo.SubscribeReqProto;
import com.example.pojo.SubscribeRespProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 自定义消息解码器
 */
public class MyNettyServerWithPB {
    public static void main(String[] args) {
        //配置服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)//配置主从线程组
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)//配置一些TCP的参数
                    .childHandler(new MyChildHandlerWithPB());//添加自定义的channel
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



class MyChildHandlerWithPB extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())//用于半包处理
                //ProtobufDecoder解码器，参数是SubscribeReq，也就是需要接收到的消息解码为SubscribeReq类型的对象
                .addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())//PB协议编码器
                .addLast(new TimeWithPBServerHandler());
    }
}


/**
 * TimeServerHandler这个才是服务端真正处理请求的服务方法
 */
class TimeWithPBServerHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStr = "TOM".equalsIgnoreCase(req.getUserName()) ?
                format.format(new Date()) + "" : "BAD ORDER";

        //受到一次请求，count加1
        System.out.println("第 " + count++ + " 次收到客户端请求：" + req.toString() + "  返回响应：" + currentTimeStr);

        SubscribeRespProto.SubscribeResp.Builder resp = SubscribeRespProto.SubscribeResp.newBuilder();
        resp.setRespCode(200)
                .setSubReqId(req.getSubReqId())
                .setDesc(currentTimeStr);

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

