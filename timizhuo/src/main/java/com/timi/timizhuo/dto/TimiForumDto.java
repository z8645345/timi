package com.timi.timizhuo.dto;

import lombok.Data;

import java.util.Date;

/**
 * 论坛dto
 * @author  Cruisin
 */
@Data
public class TimiForumDto extends BaseDto  {

    private static final long serialVersionUID = 1L;

    /**id*/
    private String forumId;
    /**标题*/
    private String forumTitle;
    /**内容*/
    private String forumContent;
    /**图片地址*/
    private String imageUrl;
    /**@好友列表*/
    private String friendIds;
    /**阅读次数*/
    private Long readCount;
    /**点赞次数*/
    private Long likeCount;
    /**回复次数*/
    private Long replyCount;
    /**是否置顶*/
    private Boolean stick;
    /**置顶排序*/
    private Integer stickGroup;
    /**帖子类型*/
    private Integer forumType;
    /**用户id*/
    private String userId;
    /**用户名字*/
    private String userName;

    /**发帖时间*/
    private Date postedTime;


    private Date createTime;
    private Date updateTime;
}
