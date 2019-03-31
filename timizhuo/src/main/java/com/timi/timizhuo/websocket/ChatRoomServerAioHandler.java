package com.timi.timizhuo.websocket;

import com.alibaba.fastjson.JSONObject;
import com.timi.timizhuo.mapper.TimiMsgLogMapper;
import com.timi.timizhuo.entity.TimiMsgLog;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.util.JSONUtils;
import com.timi.timizhuo.util.SpringUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Date;
import java.util.Objects;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/4 0004
 */
public class ChatRoomServerAioHandler implements IWsMsgHandler {

    private static Logger log = LoggerFactory.getLogger(ChatRoomServerAioHandler.class);
    public static ChatRoomServerAioHandler me = new ChatRoomServerAioHandler();

    private RedisTemplate<String, TimiUserDto> redisTemplate;

   	/**
     * 握手时走这个方法，业务可以在这里获取cookie，request参数等
     */
    @Override
    public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String clientip = request.getClientIp();
        log.info("收到来自{}的ws握手包\r\n{}", clientip, request.toString());
        String token = request.getParam("token");
        redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        TimiUserDto timiUserDto = redisTemplate.boundValueOps("USER_TOKEN" + token).get();
        channelContext.setUserid(timiUserDto.getUsername());
        redisTemplate.boundHashOps(Const.ONLINE_USER).put(timiUserDto.getUsername(), timiUserDto);
        return httpResponse;
    }	/**
     * 字节消息（binaryType = arraybuffer）过来后会走这个方法
     */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {		return null;
    }	/**
     * 当客户端发close flag时，会走这个方法
     */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequestPacket();//获取websocket握手包
        String token = httpRequest.getParam("token");
        TimiUserDto timiUserDto = redisTemplate.boundValueOps("USER_TOKEN" + token).get();
        redisTemplate.boundHashOps(Const.ONLINE_USER).delete(timiUserDto.getUsername());
        Aio.remove(channelContext, "receive close flag");		return null;
    }	/*
     * 字符消息（binaryType = blob）过来后会走这个方法
     */
    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
        if (Objects.equals("心跳内容", text)) {
            return null;
        }
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequestPacket();//获取websocket握手包
        String token = httpRequest.getParam("token");
        redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        TimiUserDto timiUserDto = redisTemplate.boundValueOps("USER_TOKEN" + token).get();
        JSONObject jsonObject = JSONObject.parseObject(text);
        TimiMsgLog timiMsgLog = new TimiMsgLog();
        timiMsgLog.setPic(timiUserDto.getPic());
        timiMsgLog.setUserId(timiUserDto.getUserId());
        timiMsgLog.setNickname(timiUserDto.getNickname());
        timiMsgLog.setMsgType(String.valueOf(jsonObject.getIntValue("type")));
        timiMsgLog.setMsg(jsonObject.getString("msg"));
        timiMsgLog.setMsgId(jsonObject.getString("msgId"));
        Date date = new Date();
        timiMsgLog.setCreateTime(date);
        timiMsgLog.setTimestamp(date.getTime());
        String msg = JSONUtils.toJosnString(timiMsgLog);


        if (ObjectUtils.notEqual(jsonObject.getIntValue("type"), 3)) {
            TimiMsgLogMapper timiMsgLogMapper = SpringUtil.getBean("timiMsgLogMapper", TimiMsgLogMapper.class);
            timiMsgLogMapper.insert(timiMsgLog);
        }

//        String msg = channelContext.getClientNode().toString() + " 说：" + text;		//用tio-websocket，服务器发送到客户端的Packet都是WsResponse
        WsResponse wsResponse = WsResponse.fromText(msg, ShowcaseServerConfig.CHARSET);		//群发
        Aio.sendToGroup(channelContext.getGroupContext(), Const.GROUP_ID, wsResponse);		//返回值是要发送给客户端的内容，一般都是返回null
        return null;
    }
}
