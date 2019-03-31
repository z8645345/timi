package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiShowDto;
import com.timi.timizhuo.entity.TimiShow;

public interface TimiShowService extends IService<TimiShow> {

    PageInfo<TimiShowDto> findPage(TimiShowDto timiShowDto);

    TimiShowDto findById(TimiShowDto timiShowDto);
}
