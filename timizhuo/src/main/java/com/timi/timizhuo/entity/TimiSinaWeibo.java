package com.timi.timizhuo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TimiSinaWeibo extends BaseEntity {

    /**
     * 依婷头像
     */
    private String timiPic;

    /**
     * 发文手机型号
     */
    private String weiboFrom;

    /**
     * 微博发布时间
     */
    private Date createdAt;

    /**
     * 微博原文地址
     */
    private String originalTextUrl;

    /**
     * 微博正文内容
     */
    private String weiboText;

    /**
     * 微博中的缩略图
     */
    private String thumbnailPics;

    /**
     * 中型图片
     */
    private String bmiddlePics;

    /**
     * 原图
     */
    private String originalPics;

    /**
     * 微博视频内容
     */
    private String weiboVedio;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * timi_sina_weibo
     */
    private static final long serialVersionUID = 1L;


}