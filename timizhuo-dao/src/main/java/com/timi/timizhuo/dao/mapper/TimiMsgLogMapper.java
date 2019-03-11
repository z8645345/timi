package com.timi.timizhuo.dao.mapper;

import com.timi.timizhuo.dao.model.TimiMsgLog;

import java.util.List;

public interface TimiMsgLogMapper {

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