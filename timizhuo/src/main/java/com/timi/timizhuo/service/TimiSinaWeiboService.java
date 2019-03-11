package com.timi.timizhuo.service;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiSinaWeiboDto;

public interface TimiSinaWeiboService {

    PageInfo<TimiSinaWeiboDto> findAllByPage(TimiSinaWeiboDto timiSinaWeiboDto);
}
