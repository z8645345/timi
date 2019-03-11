package com.timi.timizhuo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/17 001713:23
 */
@Data
public class TimiUserRecordDto {
    private String id;

    private String username;

    private String recordId;

    private String type;

    private Date createTime;
}
