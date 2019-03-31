package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TimiUser extends BaseEntity {

    @TableId(type = IdType.UUID)
    private String userId;

    private String username;

    private String password;

    private String nickname;

    private String pic;

    private String wechartCode;

    private String loveTimiDeclaration;

    private String personalProfile;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}