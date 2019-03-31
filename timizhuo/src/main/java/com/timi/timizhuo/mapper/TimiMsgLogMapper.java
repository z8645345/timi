package com.timi.timizhuo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timi.timizhuo.entity.TimiMsgLog;

import java.util.List;

public interface TimiMsgLogMapper extends BaseMapper<TimiMsgLog> {

    int deleteByPrimaryKey(String id);

    int insert(TimiMsgLog record);

    int insertSelective(TimiMsgLog record);

    TimiMsgLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TimiMsgLog record);

    int updateByPrimaryKeyWithBLOBs(TimiMsgLog record);

    int updateByPrimaryKey(TimiMsgLog record);

    List<TimiMsgLog> findAll();

    TimiMsgLog findLastMsg();
}