package com.timi.timizhuo.dao.mapper;

import com.timi.timizhuo.dao.model.TimiShow;

import java.util.List;

public interface TimiShowMapper {
    int deleteByPrimaryKey(String showId);

    int insert(TimiShow record);

    int insertSelective(TimiShow record);

    TimiShow selectByPrimaryKey(String showId);

    int updateByPrimaryKeySelective(TimiShow record);

    int updateByPrimaryKey(TimiShow record);

    List<TimiShow> findAll();
}