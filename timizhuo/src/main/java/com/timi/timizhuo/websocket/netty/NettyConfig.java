package com.timi.timizhuo.websocket.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/4 0004
 */
public class NettyConfig {

    /**
     * 储存每一个客户端接入进来时的channel对象
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    /**
     * 服务器端口号
     */
    public static final int port = 8888;
    /**
     * WebSocket地址
     */
    public static final String WEB_SOCKET_URL="ws://localhost:"+port+"/websocket";
}
