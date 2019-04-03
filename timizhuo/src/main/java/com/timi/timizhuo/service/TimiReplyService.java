package com.timi.timizhuo.service;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiForumDto;
import com.timi.timizhuo.dto.TimiReplyDto;

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
    boolean addForum(TimiReplyDto timiReplyDto);

    /**
     * 分页查询
     * @param timiReplyDto
     * @return
     */
    PageInfo<TimiReplyDto> findPage(TimiReplyDto timiReplyDto);

    /**
     * 查询置顶
     * @return
     */
    List<TimiReplyDto> findForumByStick();

    /**
     * 根据id查询
     * @param timiReplyDto
     * @return
     */
    TimiReplyDto findById(TimiReplyDto timiReplyDto);
}
