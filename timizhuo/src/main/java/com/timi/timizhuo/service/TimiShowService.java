package com.timi.timizhuo.service;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiShowDto;

public interface TimiShowService {

    PageInfo<TimiShowDto> findPage(TimiShowDto timiShowDto);

    TimiShowDto findById(TimiShowDto timiShowDto);
}
