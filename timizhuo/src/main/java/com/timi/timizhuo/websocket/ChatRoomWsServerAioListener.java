package com.timi.timizhuo.websocket;

import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.websocket.server.WsServerAioListener;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/4 0004
 */
public class ChatRoomWsServerAioListener extends WsServerAioListener {

    private static Logger log = LoggerFactory.getLogger(ChatRoomWsServerAioListener.class);
    public static final ChatRoomWsServerAioListener me = new ChatRoomWsServerAioListener();

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
        super.onAfterConnected(channelContext, isConnected, isReconnect);
        if (log.isInfoEnabled()) {
            log.info("onAfterConnected\r\n{}", channelContext);
        }
        //绑定到群组，后面会有群发
        Aio.bindGroup(channelContext, Const.GROUP_ID);
    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
        super.onAfterSent(channelContext, packet, isSentSuccess);
        if (log.isInfoEnabled()) {
            log.info("onAfterSent\r\n{}\r\n{}", packet.logstr(), channelContext);
        }
    }

    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
        RedisTemplate<String, TimiUser> redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        redisTemplate.boundHashOps(Const.ONLINE_USER).delete(channelContext.getUserid());
        super.onBeforeClose(channelContext, throwable, remark, isRemove);
        if (log.isInfoEnabled()) {
            log.info("onBeforeClose\r\n{}", channelContext);
        }
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {
        super.onAfterDecoded(channelContext, packet, packetSize);
        if (log.isInfoEnabled()) {
            log.info("onAfterDecoded\r\n{}\r\n{}", packet.logstr(), channelContext);
        }
    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {
        super.onAfterReceivedBytes(channelContext, receivedBytes);
        if (log.isInfoEnabled()) {
            log.info("onAfterReceivedBytes\r\n{}", channelContext);
        }
    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {
        super.onAfterHandled(channelContext, packet, cost);
        if (log.isInfoEnabled()) {
//        log.info("onAfterHandled\r\n{}\r\n{}", packet.logstr(), channelContext);
        }
    }
}
