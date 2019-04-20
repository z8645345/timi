package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

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

    /**
     * 粉丝数
     */
    @TableField(exist = false)
    private Integer fansCount;

    /**
     * 关注数
     */
    @TableField(exist = false)
    private Integer followCount;

    /**
     * 发帖数
     */
    @TableField(exist = false)
    private Integer forumCount;

    /**
     * 未读消息数
     */
    @TableField(exist = false)
    private Integer messageCount;

    /**
     * 是否关注当前登录用户
     */
    @TableField(exist = false)
    private Boolean isFollowMe = false;

    /**
     * 当前用户是否关注该用户
     */
    @TableField(exist = false)
    private Boolean isFollow = false;

    private static final long serialVersionUID = 1L;
}