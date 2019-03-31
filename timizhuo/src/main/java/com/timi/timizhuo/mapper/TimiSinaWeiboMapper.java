package com.timi.timizhuo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timi.timizhuo.entity.TimiSinaWeibo;

public interface TimiSinaWeiboMapper extends BaseMapper<TimiSinaWeibo> {

    int insert(TimiSinaWeibo record);

    /**
     * 更新发文时间查询最新的一条微博信息
     * @return
     */
    TimiSinaWeibo findNewestWeibo();

}