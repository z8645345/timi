package com.timi.timizhuo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TimiReply extends BaseEntity<TimiReply> {

    /**内容*/
    private String replyCotent;
    /**主回复id*/
    private String parentId;
    /**主贴id*/
    private String forumId;
    /**点赞次数*/
    private Long likeCount;
    /**楼层号*/
    private Integer tierNum;
    /**回复时间*/
    private Date replyTime;
    /**用户id*/
    private String userId;
    /**用户名字*/
    private String userName;
    /**用户图片地址*/
    private String userImageUrl;
    /**回复数*/
    private Integer replyNum ;
    /**回复类型  1 主贴回复  2 楼层回复  3 子回复回复*/
    private Integer replyType ;


    private Date createTime;
    private Date updateTime;

}
