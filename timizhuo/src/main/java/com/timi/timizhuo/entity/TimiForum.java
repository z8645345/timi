package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TimiForum extends BaseEntity {

    /**标题*/
    private String forumTitle;
    /**内容*/
    private String forumContent;
    /**图片地址*/
    private String imageUrl;
    /**@好友列表*/
    @TableField(exist = false)
    private List<String> friendIds;
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

    /**用户图片地址*/
    private String userImageUrl;

    @TableField(exist = false)
    private Long postedTimeLong;
    /**
     * 类型判断1 为点赞   2为阅读
     */
    @TableField(exist = false)
    private Integer type;

}
