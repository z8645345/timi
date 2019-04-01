package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class TimiVideo extends BaseEntity {

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

    @TableField(exist = false)
    private Boolean userLove;

    @TableField(exist = false)
    private Boolean userCollection;

    private static final long serialVersionUID = 1L;

}