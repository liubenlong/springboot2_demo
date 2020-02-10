package com.example;

import com.example.pojo.SubscribeReqProto;
import com.example.pojo.SubscribeRespProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 自定义消息解码器
 */
public class MyNettyClientWithPB {
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
                            ch.pipeline()
                                    .addLast(new ProtobufVarint32FrameDecoder())
                                    .addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()))
                                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                                    .addLast(new ProtobufEncoder())
                                    .addLast(new TimeWithPBClientHandler());

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
class TimeWithPBClientHandler extends ChannelInboundHandlerAdapter {
    private int count = 0;

    /**
     * 客户端与服务器TCP链路链接成功后调用该方法
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        List<String> list = Arrays.asList(new String[]{"杭州","北京"});
        for (int i = 0; i < 100; i++) {
            SubscribeReqProto.SubscribeReq.Builder req = SubscribeReqProto.SubscribeReq.newBuilder();
            req.setSubReqId(i)
                    .setUserName("TOM")
                    .setProductName(UUID.randomUUID().toString())
                    .addAllAddress(list);

            ctx.writeAndFlush(req);//写入缓冲并且发送到socketchannel
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
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        SubscribeRespProto.SubscribeResp resp = (SubscribeRespProto.SubscribeResp) msg;
        System.out.println("第 " + count++ + " 次受到服务端返回：" + resp.getDesc());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }

}


