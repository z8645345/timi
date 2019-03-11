package com.timi.timizhuo.dao.mapper;

import com.timi.timizhuo.dao.model.TimiImages;
import com.timi.timizhuo.dao.model.TimiSinaWeibo;

import java.util.List;

public interface TimiSinaWeiboMapper {
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