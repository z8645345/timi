package com.timi.timizhuo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description 卓依婷视频数据传输类
 * @Auther timi
 * @Date 2018/12/2 000215:28
 */
@Data
public class TimiVideoDto extends BaseDto {

    private String id;

    private String videoTitle;

    private String videoCover;

    private String videoContentUrl;

    private String videoTime;

    private Long loveCount;

    private Long showCount;

    private Long collectionCount;

    private String columnNo;

    private String columnName;

    private Date createTime;

    private Date updateTime;

    private Boolean play;

    private boolean userLove;

    private boolean userCollection;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoTitle=").append(videoTitle);
        sb.append(", videoCover=").append(videoCover);
        sb.append(", videoContentUrl=").append(videoContentUrl);
        sb.append(", videoTime=").append(videoTime);
        sb.append(", loveCount=").append(loveCount);
        sb.append(", showCount=").append(showCount);
        sb.append(", collectionCount=").append(collectionCount);
        sb.append(", columnNo=").append(columnNo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
