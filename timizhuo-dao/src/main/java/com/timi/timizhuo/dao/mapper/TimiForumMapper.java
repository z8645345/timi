package com.timi.timizhuo.dao.mapper;

import com.timi.timizhuo.dao.model.TimiForum;

import java.util.List;

public interface TimiForumMapper {

    int insert(TimiForum timiForum);

    /**
     * 根据条件查询
     * @param timiForum
     * @return
     */
    List<TimiForum> findByCondition(TimiForum timiForum);
}