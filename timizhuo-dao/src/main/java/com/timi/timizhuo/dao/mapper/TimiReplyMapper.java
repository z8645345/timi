package com.timi.timizhuo.dao.mapper;

import com.timi.timizhuo.dao.model.TimiReply;

import java.util.List;

public interface TimiReplyMapper {

    int insert(TimiReply timiReply);

    /**
     * 根据条件查询
     * @param timiReply
     * @return
     */
    List<TimiReply> findByCondition(TimiReply timiReply);

    /**
     * 根据主贴id查询是否相关帖子且类型为帖子的 按回复时间倒叙
     */
    List<TimiReply>findByForumId(String forumId);
    /**
     * 根据id查询
     */
    TimiReply  findById(String replyId);

    int updateReplyNum(TimiReply upReply);
}