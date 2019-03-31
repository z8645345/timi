package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiSinaWeiboDto;
import com.timi.timizhuo.entity.TimiSinaWeibo;

public interface TimiSinaWeiboService extends IService<TimiSinaWeibo> {

    PageInfo<TimiSinaWeiboDto> findAllByPage(TimiSinaWeiboDto timiSinaWeiboDto);
}
