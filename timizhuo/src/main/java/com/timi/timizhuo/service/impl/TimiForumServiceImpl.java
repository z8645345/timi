package com.timi.timizhuo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dao.mapper.TimiForumMapper;
import com.timi.timizhuo.dao.model.TimiForum;
import com.timi.timizhuo.dto.TimiForumDto;
import com.timi.timizhuo.service.TimiForumService;
import com.timi.timizhuo.util.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 论坛service
 *
 * @author Cruisin
 */
@Service
@Slf4j
public class TimiForumServiceImpl implements TimiForumService {


    @Autowired
    private TimiForumMapper timiForumMapper;

    @Override
    public boolean addForum(TimiForumDto timiForumDto) {
        if (timiForumDto == null) {
            log.warn("reques timiForumDto is null ");
            return false;
        }
        TimiForum timiForum = new TimiForum();
        BeanConvertUtils.convert(timiForumDto, timiForum);
        timiForum.setCreateTime(new Date());
        timiForum.setUpdateTime(timiForum.getCreateTime());
        timiForum.setPostedTime(timiForum.getCreateTime());
        return this.timiForumMapper.insert(timiForum) == 1;
    }

    /**
     * 分页查询
     * @param timiForumDto
     * @return
     */
    @Override
    public PageInfo<TimiForumDto> findPage(TimiForumDto timiForumDto) {
        String columnNo = null;
        if (timiForumDto == null) {
            return null;
        }
        TimiForum timiForum = new TimiForum();
        BeanConvertUtils.convert(timiForumDto, timiForum);
        PageHelper.startPage(timiForumDto.getPageNum(), timiForumDto.getPageSize());
        List<TimiForum> timiForums =this.timiForumMapper.findByCondition(timiForum);
        List<TimiForumDto> list = BeanConvertUtils.convertList(timiForums, TimiForumDto.class);
        PageInfo<TimiForumDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<TimiForumDto> findForumByStick() {
        TimiForum timiForum = new TimiForum();
        timiForum.setStick(true);
        List<TimiForum> timiForums =this.timiForumMapper.findByCondition(timiForum);
        List<TimiForumDto> list = BeanConvertUtils.convertList(timiForums, TimiForumDto.class);
        return list;
    }
}
