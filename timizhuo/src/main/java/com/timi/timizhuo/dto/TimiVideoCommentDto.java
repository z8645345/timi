package com.timi.timizhuo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/19 001907:21
 */
@Data
public class TimiVideoCommentDto extends BaseDto {

    private String id;

    private String commentBy;

    private String videoId;

    private String commentText;

    private String superComment;

    private Integer loveCount;

    private Integer status;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    private TimiVideoCommentDto subCommentDto;

    private TimiUserDto timiUserDto;
}
