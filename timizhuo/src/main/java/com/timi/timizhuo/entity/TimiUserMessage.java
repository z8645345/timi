package com.timi.timizhuo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author timi
 * @since 2019-04-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TimiUserMessage extends BaseEntity<TimiUserMessage> {

    private static final long serialVersionUID = 1L;

    private String userId;

    /**
     * 主回复1 楼层回复2 子回复3 帖子 4
     */
    private Integer contentType;

    private String forumId;

    private String replyId;

    /**
     * 消息状态 1 未读 2已读  3失效
     */
    private Integer messageState;



}
