package com.timi.timizhuo.dto;

import lombok.Data;

/**
 * @Description 邮件实体传输类
 * @Auther timi
 * @Date 2018/12/6 000612:45
 */
@Data
public class EmaillDto {

    /**
     * 收件人邮件
     */
    String to;

    /**
     * 邮件标题
     */
    String title;

    /**
     * 消息体
     */
    String text;
}
