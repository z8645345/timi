package com.timi.timizhuo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timi.timizhuo.entity.TimiForum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimiForumMapper extends BaseMapper<TimiForum> {

    /**
     * 根据条件查询
     * @param timiForum
     * @return
     */
    List<TimiForum> findByCondition(TimiForum timiForum);

    /**
     * 更新回复数
     * @param timiForum
     */
    void updateReplyCountById(TimiForum timiForum);
}