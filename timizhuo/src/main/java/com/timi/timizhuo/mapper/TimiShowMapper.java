package com.timi.timizhuo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timi.timizhuo.entity.TimiShow;

import java.util.List;

public interface TimiShowMapper extends BaseMapper<TimiShow> {
    int deleteByPrimaryKey(String showId);

    int insert(TimiShow record);

    int insertSelective(TimiShow record);

    TimiShow selectByPrimaryKey(String showId);

    int updateByPrimaryKeySelective(TimiShow record);

    int updateByPrimaryKey(TimiShow record);

    List<TimiShow> findAll();
}