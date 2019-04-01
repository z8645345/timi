package com.timi.timizhuo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TimiMsgLog extends BaseEntity {

    private String userId;

    private String msgType;

    private Date createTime;

    private Date updateTime;

    private String msg;

    private String pic;

    private String nickname;

    private String msgId;

    private Long timestamp;

    private static final long serialVersionUID = 1L;
}