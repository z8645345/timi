package com.timi.timizhuo.websocket;

import org.tio.server.ServerGroupContext;
import org.tio.websocket.server.WsServerStarter;

import java.io.IOException;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/4 0004
 */
public class ChatRoomServerStarter {

    private WsServerStarter wsServerStarter;	private ServerGroupContext serverGroupContext;	/**
     *
     * @author tanyaowu
     */
    public ChatRoomServerStarter(int port, ChatRoomServerAioHandler wsMsgHandler) throws IOException {
        wsServerStarter = new WsServerStarter(port, wsMsgHandler);

        serverGroupContext = wsServerStarter.getServerGroupContext();
        serverGroupContext.setName(ShowcaseServerConfig.PROTOCOL_NAME);
        serverGroupContext.setServerAioListener(ChatRoomWsServerAioListener.me);		//设置ip统计时间段
        serverGroupContext.ipStats.addDurations(ShowcaseServerConfig.IpStatDuration.IPSTAT_DURATIONS);		//设置ip监控
        serverGroupContext.setIpStatListener(ShowcaseIpStatListener.me);		//设置心跳超时时间
        serverGroupContext.setHeartbeatTimeout(ShowcaseServerConfig.HEARTBEAT_TIMEOUT);
    }	/**
     * @param
     * @author tanyaowu
     * @throws IOException
     */
    public static void start() throws IOException {
        ChatRoomServerStarter appStarter = new ChatRoomServerStarter(ShowcaseServerConfig.SERVER_PORT, ChatRoomServerAioHandler.me);
        appStarter.wsServerStarter.start();
    }	/**
     * @return the serverGroupContext
     */
    public ServerGroupContext getServerGroupContext() {
        return serverGroupContext;
    }

    public WsServerStarter getWsServerStarter() {
        return wsServerStarter;
    }
    public static void main(String[] args) throws IOException {
        start();
    }
}
