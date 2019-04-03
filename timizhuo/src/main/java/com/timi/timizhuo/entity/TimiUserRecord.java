package com.timi.timizhuo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TimiUserRecord extends BaseEntity {

    private String username;

    private String recordId;

    private String type;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}