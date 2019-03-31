package com.timi.timizhuo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.mapper.TimiShowMapper;
import com.timi.timizhuo.entity.TimiShow;
import com.timi.timizhuo.dto.TimiShowDto;
import com.timi.timizhuo.service.TimiShowService;
import com.timi.timizhuo.util.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimiShowServiceImpl implements TimiShowService {

    @Autowired
    private TimiShowMapper timiShowMapper;

    @Override
    public PageInfo<TimiShowDto> findPage(TimiShowDto timiShowDto) {
        PageHelper.startPage(timiShowDto.getPageNum(), timiShowDto.getPageSize());
        List<TimiShow> listModel = timiShowMapper.findAll();
        List<TimiShowDto> list = BeanConvertUtils.convertList(listModel, TimiShowDto.class);
        PageInfo<TimiShowDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public TimiShowDto findById(TimiShowDto timiShowDto) {
        TimiShow timiShow = timiShowMapper.selectByPrimaryKey(timiShowDto.getShowId());
        BeanConvertUtils.convert(timiShow, timiShowDto);
        return timiShowDto;
    }
}
