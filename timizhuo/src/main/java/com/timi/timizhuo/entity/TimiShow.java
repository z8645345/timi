package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TimiShow extends BaseEntity {
    /**
     * 演出ID
     */
    @TableId(type = IdType.UUID)
    private String showId;

    /**
     * 演出主题
     */
    private String showTitle;

    /**
     * 演出时间
     */
    private Date showTime;

    /**
     * 演出城市
     */
    private String showCity;

    /**
     * 演出地址
     */
    private String showAddress;

    /**
     * 演出详情
     */
    private String showDetail;

    /**
     * 演出宣传图片地址，多张英文逗号隔开
     */
    private String showImgs;

    /**
     * 演出宣传视频
     */
    private String showVideo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * timi_show
     */
    private static final long serialVersionUID = 1L;

}