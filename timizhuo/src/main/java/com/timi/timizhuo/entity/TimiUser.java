package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class TimiUser extends BaseEntity {

    private String username;

    private String password;

    private String nickname;

    private String pic;

    private String wechartCode;

    private String loveTimiDeclaration;

    private String personalProfile;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private String checkCode;

    @TableField(exist = false)
    private String token;

    @TableField(exist = false)
    private String headerImage;

    @TableField(exist = false)
    private String msg;

    @TableField(exist = false)
    private String msgId;

    @TableField(exist = false)
    private Integer msgType;

    private static final long serialVersionUID = 1L;
}