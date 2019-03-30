package com.timi.timizhuo.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TimiSinaWeibo implements Serializable {
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

    /**
     * 主键ID
     * @return weibo_id 主键ID
     */
    public String getWeiboId() {
        return weiboId;
    }

    /**
     * 主键ID
     * @param weiboId 主键ID
     */
    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId == null ? null : weiboId.trim();
    }

    /**
     * 依婷头像
     * @return timi_pic 依婷头像
     */
    public String getTimiPic() {
        return timiPic;
    }

    /**
     * 依婷头像
     * @param timiPic 依婷头像
     */
    public void setTimiPic(String timiPic) {
        this.timiPic = timiPic == null ? null : timiPic.trim();
    }

    /**
     * 发文手机型号
     * @return weibo_from 发文手机型号
     */
    public String getWeiboFrom() {
        return weiboFrom;
    }

    /**
     * 发文手机型号
     * @param weiboFrom 发文手机型号
     */
    public void setWeiboFrom(String weiboFrom) {
        this.weiboFrom = weiboFrom == null ? null : weiboFrom.trim();
    }

    /**
     * 微博发布时间
     * @return created_at 微博发布时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 微博发布时间
     * @param createdAt 微博发布时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 微博原文地址
     * @return original_text_url 微博原文地址
     */
    public String getOriginalTextUrl() {
        return originalTextUrl;
    }

    /**
     * 微博原文地址
     * @param originalTextUrl 微博原文地址
     */
    public void setOriginalTextUrl(String originalTextUrl) {
        this.originalTextUrl = originalTextUrl == null ? null : originalTextUrl.trim();
    }

    /**
     * 微博正文内容
     * @return weibo_text 微博正文内容
     */
    public String getWeiboText() {
        return weiboText;
    }

    /**
     * 微博正文内容
     * @param weiboText 微博正文内容
     */
    public void setWeiboText(String weiboText) {
        this.weiboText = weiboText == null ? null : weiboText.trim();
    }

    /**
     * 微博中的缩略图
     * @return thumbnail_pics 微博中的缩略图
     */
    public String getThumbnailPics() {
        return thumbnailPics;
    }

    /**
     * 微博中的缩略图
     * @param thumbnailPics 微博中的缩略图
     */
    public void setThumbnailPics(String thumbnailPics) {
        this.thumbnailPics = thumbnailPics == null ? null : thumbnailPics.trim();
    }

    /**
     * 中型图片
     * @return bmiddle_pics 中型图片
     */
    public String getBmiddlePics() {
        return bmiddlePics;
    }

    /**
     * 中型图片
     * @param bmiddlePics 中型图片
     */
    public void setBmiddlePics(String bmiddlePics) {
        this.bmiddlePics = bmiddlePics == null ? null : bmiddlePics.trim();
    }

    /**
     * 原图
     * @return original_pics 原图
     */
    public String getOriginalPics() {
        return originalPics;
    }

    /**
     * 原图
     * @param originalPics 原图
     */
    public void setOriginalPics(String originalPics) {
        this.originalPics = originalPics == null ? null : originalPics.trim();
    }

    /**
     * 微博视频内容
     * @return weibo_vedio 微博视频内容
     */
    public String getWeiboVedio() {
        return weiboVedio;
    }

    /**
     * 微博视频内容
     * @param weiboVedio 微博视频内容
     */
    public void setWeiboVedio(String weiboVedio) {
        this.weiboVedio = weiboVedio == null ? null : weiboVedio.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

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