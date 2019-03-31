package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TimiVideoComment implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    private String commentBy;

    private String videoId;

    private String commentText;

    private String superComment;

    private Integer loveCount;

    private Integer status;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commentBy=").append(commentBy);
        sb.append(", videoId=").append(videoId);
        sb.append(", commentText=").append(commentText);
        sb.append(", superComment=").append(superComment);
        sb.append(", loveCount=").append(loveCount);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}