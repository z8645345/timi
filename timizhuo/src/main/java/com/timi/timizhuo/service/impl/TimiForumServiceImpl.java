package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.entity.TimiForum;
import com.timi.timizhuo.enums.ForumEnum;
import com.timi.timizhuo.mapper.TimiForumMapper;
import com.timi.timizhuo.service.TimiForumService;
import com.timi.timizhuo.util.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 论坛service
 *
 * @author Cruisin
 */
@Service
@Slf4j
public class TimiForumServiceImpl extends ServiceImpl<TimiForumMapper, TimiForum> implements TimiForumService {

    @Autowired
    private TimiForumMapper timiForumMapper;

    @Override
    @Transactional
    public boolean addForum(TimiForum timiForumDto) {
        if (timiForumDto == null) {
            log.warn("reques timiForumDto is null ");
            return false;
        }
        //        if(StringUtils.isNotBlank(timiForumDto.getFriendIds())){
//            try {
//                String ids = timiForumDto.getFriendIds();
//                String[] split = ids.split(",");
//            } catch (Exception e) {
//                log.error("处理friendIds转换异常 ",e);
//                return false;
//            }
//
//        }
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
    public PageInfo<TimiForum> findPage(TimiForum timiForumDto) {
        if (timiForumDto == null) {
            return null;
        }
        TimiForum timiForum = new TimiForum();
        BeanConvertUtils.convert(timiForumDto, timiForum);
        PageHelper.startPage(timiForumDto.getPageNum(), timiForumDto.getPageSize());
        List<TimiForum> timiForums =this.timiForumMapper.findByCondition(timiForum);
        PageInfo<TimiForum> pageInfo = new PageInfo<>(timiForums);
        return pageInfo;
    }

    @Override
    public List<TimiForum> findForumByStick() {
        TimiForum timiForum = new TimiForum();
        timiForum.setStick(true);
        List<TimiForum> timiForums =this.timiForumMapper.findByCondition(timiForum);
        return timiForums;
    }

    @Override
    public List<TimiForum> findForumByUserId(String id) {
        if (StringUtils.isBlank(id)) return null;
        List<TimiForum> timiForums = this.timiForumMapper.selectList(new QueryWrapper<TimiForum>().eq("user_id", id));
        return timiForums;
    }

    @Override
    public boolean updateLikeAndRead(TimiForum timiForum) {

        if (timiForum == null || StringUtils.isBlank(timiForum.getId())
                || timiForum.getType() == null) return false;

        TimiForum byId = this.timiForumMapper.selectById(timiForum.getId());
        if (byId == null) {
            log.warn("数据有误,id为空");
            return false;
        }
        if (timiForum.getType() == ForumEnum.TypeEnum.LIKE.getValue()) {
            Long likeCount = byId.getLikeCount();
            if (likeCount == null) {
                likeCount = 1L;
            } else {
                ++likeCount;
            }
            timiForum.setLikeCount(likeCount);
            return this.timiForumMapper.updateById(timiForum) == 1;
        } else {
            Long readCount = byId.getReadCount();
            if (readCount == null) {
                readCount = 1L;
            } else {
                ++readCount;
            }
            timiForum.setLikeCount(readCount);
            return this.timiForumMapper.updateById(timiForum) == 1;
        }
    }

    @Override
    public TimiForum findForumById(TimiForum timiForum) {
        if (timiForum == null || StringUtils.isBlank(timiForum.getId())) return null;
        return this.timiForumMapper.selectById(timiForum);
    }
}
