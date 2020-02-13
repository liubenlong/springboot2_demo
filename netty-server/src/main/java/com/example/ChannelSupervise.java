package com.example;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelSupervise {
    /**
     * ChannelGroup是netty提供用于管理web于服务器建立的通道channel的，
     * 其本质是一个高度封装的set集合，在服务器广播消息时，
     * 可以直接通过它的writeAndFlush将消息发送给集合中的所有通道中去
     */
    private static ChannelGroup GlobalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    /**
     * ChannelMap维护的是channelID和channel的对应关系，用于向指定channel发送消息
     */
    private static ConcurrentMap<String, ChannelId> ChannelMap = new ConcurrentHashMap<>();

    public static void addChannel(Channel channel) {
        GlobalGroup.add(channel);
        ChannelMap.put(channel.id().asShortText(), channel.id());
    }

    public static void removeChannel(Channel channel) {
        GlobalGroup.remove(channel);
        ChannelMap.remove(channel.id().asShortText());
    }

    //找到某个channel来发送消息
    public static Channel findChannel(String id) {
        return GlobalGroup.find(ChannelMap.get(id));
    }

    public static void send2All(TextWebSocketFrame tws) {
        GlobalGroup.writeAndFlush(tws);
    }

    public static int count() {
        return GlobalGroup.size();
    }
}