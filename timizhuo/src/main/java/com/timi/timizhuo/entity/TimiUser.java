package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class TimiUser extends BaseEntity {
    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //头像
    private String pic;
    //微信号
    private String wechartCode;
    //爱婷宣言
    private String loveTimiDeclaration;
    //个人简介
    private String personalProfile;
    //创建时间
    private Date createTime;
    //更新时间
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