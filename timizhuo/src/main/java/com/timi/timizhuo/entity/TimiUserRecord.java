package com.timi.timizhuo.entity;

import lombok.Data;

@Data
public class TimiUserRecord extends BaseEntity {

    private String username;

    private String recordId;

    private String type;

    private static final long serialVersionUID = 1L;
}