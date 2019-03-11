package com.timi.timizhuo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dao.mapper.TimiSinaWeiboMapper;
import com.timi.timizhuo.dao.model.TimiSinaWeibo;
import com.timi.timizhuo.dto.TimiSinaWeiboDto;
import com.timi.timizhuo.service.TimiSinaWeiboService;
import com.timi.timizhuo.util.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimiSinaWeiboServiceImpl implements TimiSinaWeiboService {

    @Autowired
    private TimiSinaWeiboMapper timiSinaWeiboMapper;

    @Override
    public PageInfo<TimiSinaWeiboDto> findAllByPage(TimiSinaWeiboDto timiSinaWeiboDto) {
        PageHelper.startPage(timiSinaWeiboDto.getPageNum(), timiSinaWeiboDto.getPageSize());
        List<TimiSinaWeibo> modelList =  timiSinaWeiboMapper.findAll();
        List<TimiSinaWeiboDto> list = BeanConvertUtils.convertList(modelList, TimiSinaWeiboDto.class);
        PageInfo<TimiSinaWeiboDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
