package com.timi.timizhuo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timi.timizhuo.entity.TimiSinaWeibo;

import java.util.List;

public interface TimiSinaWeiboMapper extends BaseMapper<TimiSinaWeibo> {
    int deleteByPrimaryKey(String weiboId);

    int insert(TimiSinaWeibo record);

    int insertSelective(TimiSinaWeibo record);

    TimiSinaWeibo selectByPrimaryKey(String weiboId);

    int updateByPrimaryKeySelective(TimiSinaWeibo record);

    int updateByPrimaryKey(TimiSinaWeibo record);

    List<TimiSinaWeibo> findAll();

    /**
     * 更新发文时间查询最新的一条微博信息
     * @return
     */
    TimiSinaWeibo findNewestWeibo();

}