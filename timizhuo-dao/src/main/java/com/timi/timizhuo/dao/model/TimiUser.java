package com.timi.timizhuo.dao.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TimiUser implements Serializable {

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