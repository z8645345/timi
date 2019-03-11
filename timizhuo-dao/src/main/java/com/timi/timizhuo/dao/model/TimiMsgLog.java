package com.timi.timizhuo.dao.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TimiMsgLog implements Serializable {

    private String id;

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