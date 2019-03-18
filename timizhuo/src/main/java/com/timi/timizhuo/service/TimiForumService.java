package com.timi.timizhuo.service;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiForumDto;

import java.util.List;

/**
 * 论坛service
 * @author Cruisin
 */
public interface TimiForumService {
    /**
     *发帖
     * @param timiForumDto
     * @return
     */
    boolean addForum(TimiForumDto timiForumDto);

    /**
     * 分页查询
     * @param timiForumDto
     * @return
     */
    PageInfo<TimiForumDto> findPage(TimiForumDto timiForumDto);

    /**
     * 查询置顶
     * @return
     */
    List<TimiForumDto> findForumByStick();
}
