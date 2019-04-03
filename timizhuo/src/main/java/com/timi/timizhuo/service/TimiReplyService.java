package com.timi.timizhuo.service;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.entity.TimiReply;

import java.util.List;

/**
 * 回复
 * @author Cruisin
 */
public interface TimiReplyService {
    /**
     *回复
     * @param timiReplyDto
     * @return
     */
    boolean addForum(TimiReply timiReplyDto);

    /**
     * 分页查询
     * @param timiReplyDto
     * @return
     */
    PageInfo<TimiReply> findPage(TimiReply timiReplyDto);

    /**
     * 查询置顶
     * @return
     */
    List<TimiReply> findForumByStick();

}
