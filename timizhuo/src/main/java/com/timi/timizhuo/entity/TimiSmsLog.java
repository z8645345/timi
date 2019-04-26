package com.timi.timizhuo.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.timi.timizhuo.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 短信日志表
 * </p>
 *
 * @author timi
 * @since 2019-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TimiSmsLog extends BaseEntity<TimiSmsLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private String code;

    /**
     * 返回提示
     */
    private String msg;

    /**
     * 成功扣费条数
     */
    private String count;

    /**
     * smsid
     */
    private String smsid;

    /**
     * 手机号码
     */
    private String mobile;

}
