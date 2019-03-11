package com.timi.timizhuo.websocket;

import org.tio.core.intf.Packet;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/4 0004
 */
public class ChatRoomPacket extends Packet {
    private static final long serialVersionUID = -172060606924066412L;
    public static final int HEADER_LENGHT = 4;//消息头的长度
    public static final String CHARSET = "utf-8";
    private byte[] body;

    /**
     * @return the body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(byte[] body) {
        this.body = body;
    }
}
