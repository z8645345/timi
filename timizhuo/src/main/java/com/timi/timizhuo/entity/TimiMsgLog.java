package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TimiMsgLog extends BaseEntity {

    @TableId(type = IdType.UUID)
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