package com.timi.timizhuo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/8 0008
 */
@Data
public class TimiMsgLogDTO extends BaseDto {

    private String id;

    private String userId;

    private String msgType;

    private Date createTime;

    private Date updateTime;

    private String msg;

    private String pic;

    private String nickname;

    private Long timestamp;

    private static final long serialVersionUID = 1L;
}
