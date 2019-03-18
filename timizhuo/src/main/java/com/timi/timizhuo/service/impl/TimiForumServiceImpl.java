package com.timi.timizhuo.service.impl;

import com.timi.timizhuo.dao.mapper.TimiForumMapper;
import com.timi.timizhuo.dao.model.TimiForum;
import com.timi.timizhuo.dao.model.TimiUserRecord;
import com.timi.timizhuo.dto.TimiForumDto;
import com.timi.timizhuo.service.TimiForumService;
import com.timi.timizhuo.util.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
