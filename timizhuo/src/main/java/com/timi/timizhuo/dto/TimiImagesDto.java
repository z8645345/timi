package com.timi.timizhuo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description 卓依婷图片数据传输类
 * @Auther timi
 * @Date 2018/12/9 000914:41
 */
@Data
public class TimiImagesDto extends BaseDto {

    private String id;

    private String imgUrl;

    private String thumbnailUrl;

    private String alt;

    private String columnNo;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", alt=").append(alt);
        sb.append(", columnNo=").append(columnNo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
