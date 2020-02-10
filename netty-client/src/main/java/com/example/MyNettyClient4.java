package com.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 自定义消息解码器
 */
public class MyNettyClient4 {
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
                                    .addLast(new MyCustomMessageEncoder())
                                    .addLast(new MyCustomMessageDecoder())
                                    .addLast(new TimeClientHandler4());

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
            System.out.println("-----------------");
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

//客户端业务逻辑处理类
class TimeClientHandler4 extends ChannelInboundHandlerAdapter {
    private int count = 0;

    /**
     * 客户端与服务器TCP链路链接成功后调用该方法
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        byte[] req = ("QUERY TIME ORDER").getBytes();
        for (int i = 0; i < 40; i++) {
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
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String body = (String) msg;
        System.out.println("第 " + count++ + " 次受到服务端返回：" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }

}


