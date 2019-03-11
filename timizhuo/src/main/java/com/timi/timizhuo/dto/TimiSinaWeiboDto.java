package com.timi.timizhuo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TimiSinaWeiboDto extends BaseDto {

    /**
     * 主键ID
     */
    private String weiboId;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", weiboId=").append(weiboId);
        sb.append(", timiPic=").append(timiPic);
        sb.append(", weiboFrom=").append(weiboFrom);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", originalTextUrl=").append(originalTextUrl);
        sb.append(", weiboText=").append(weiboText);
        sb.append(", thumbnailPics=").append(thumbnailPics);
        sb.append(", bmiddlePics=").append(bmiddlePics);
        sb.append(", originalPics=").append(originalPics);
        sb.append(", weiboVedio=").append(weiboVedio);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
