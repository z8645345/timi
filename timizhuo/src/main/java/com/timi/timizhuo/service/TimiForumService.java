package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.entity.TimiForum;

import java.util.List;

/**
 * 论坛service
 * @author Cruisin
 */
public interface TimiForumService extends IService<TimiForum> {
    /**
     *发帖
     * @param timiForumDto
     * @return
     */
    boolean addForum(TimiForum timiForumDto);

    /**
     * 分页查询
     * @param timiForumDto
     * @return
     */
    PageInfo<TimiForum> findPage(TimiForum timiForumDto);

    /**
     * 查询置顶
     * @return
     */
    List<TimiForum> findForumByStick();

    /**
     * 用户id查询
     * @param id
     * @return
     */
    List<TimiForum> findForumByUserId(String id);

    boolean updateLikeAndRead(TimiForum timiForum);

    /**
     * 根据id查询
     * @param timiForum
     * @return
     */
    TimiForum findForumById(TimiForum timiForum);
}
