package com.timi.timizhuo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timi.timizhuo.entity.TimiReply;

import java.util.List;

public interface TimiReplyMapper extends BaseMapper<TimiReply> {

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

    int updateReplyNum(TimiReply upReply);

    List<TimiReply> findByParentId(TimiReply queryReply);
}