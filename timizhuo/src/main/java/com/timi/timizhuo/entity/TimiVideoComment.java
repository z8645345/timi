package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TimiVideoComment extends BaseEntity {

    private String commentBy;

    private String videoId;

    private String commentText;

    private String superComment;

    private Integer loveCount;

    private Integer status;

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private TimiUser timiUser;
}