package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TimiMsgLog extends BaseEntity {

    private String userId;

    private String msgType;

    private String msg;

    @TableField(exist = false)
    private String pic;

    @TableField(exist = false)
    private String nickname;

    @TableField(exist = false)
    private String msgId;

    @TableField(exist = false)
    private Long timestamp;

    private static final long serialVersionUID = 1L;
}