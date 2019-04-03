package com.timi.timizhuo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timi.timizhuo.entity.TimiForum;

import java.util.List;

public interface TimiForumMapper extends BaseMapper<TimiForum> {

    /**
     * 根据条件查询
     * @param timiForum
     * @return
     */
    List<TimiForum> findByCondition(TimiForum timiForum);
}