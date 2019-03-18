package com.timi.timizhuo.service;

import com.timi.timizhuo.dto.TimiForumDto;

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
}
