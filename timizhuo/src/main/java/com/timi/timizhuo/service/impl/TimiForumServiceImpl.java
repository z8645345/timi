package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.entity.TimiForum;
import com.timi.timizhuo.entity.TimiReply;
import com.timi.timizhuo.entity.TimiUserMessage;
import com.timi.timizhuo.enums.ForumEnum;
import com.timi.timizhuo.mapper.TimiForumMapper;
import com.timi.timizhuo.mapper.TimiReplyMapper;
import com.timi.timizhuo.service.TimiForumService;
import com.timi.timizhuo.service.TimiUserMessageService;
import com.timi.timizhuo.util.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    @Autowired
    private TimiUserMessageService timiUserMessageService;

    @Autowired
    private TimiReplyMapper timiReplyMapper;
    @Override
    public boolean addForum(TimiForum timiForumDto) {
        if (timiForumDto == null) {
            log.warn("reques timiForumDto is null ");
            return false;
        }
        TimiForum timiForum = new TimiForum();
        BeanConvertUtils.convert(timiForumDto, timiForum);
        timiForum.setCreateTime(new Date());
        timiForum.setUpdateTime(timiForum.getCreateTime());
        timiForum.setPostedTime(timiForum.getCreateTime());
        this.timiForumMapper.insert(timiForum);

        String forumId = timiForum.getId();

        if (!CollectionUtils.isEmpty(timiForumDto.getFriendIds())) {
            List<TimiUserMessage> timiUserMessages = new ArrayList<>();
            //不为空 处理好友入消息表
            List<String> friendIds = timiForumDto.getFriendIds();
            friendIds.forEach(id -> {
                TimiUserMessage timiUserMessage = new TimiUserMessage();
                timiUserMessage.setUserId(id);
                timiUserMessage.setForumId(forumId);
                timiUserMessage.setContentType(4);
                timiUserMessage.setMessageState(1);
                timiUserMessage.setCreateTime(new Date());
                timiUserMessage.setUpdateTime(timiUserMessage.getCreateTime());
                timiUserMessages.add(timiUserMessage);
            });
        }
        return true;
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

    @Async
    @Override
    public void asyncUpdateReplyreplyCount(String id) {
        TimiReply timiReply = new TimiReply();
        timiReply.setForumId(id);
        List<TimiReply> timiReplyList = timiReplyMapper.findByForumId(timiReply);
        Long replyCount = 0L;
        if (!CollectionUtils.isEmpty(timiReplyList)) {
            replyCount = (long) timiReplyList.size();
        }
        TimiForum timiForum = timiForumMapper.selectById(id);
        if (timiForum.getReplyCount() < replyCount) {
            // 原来的回复数小于实际的回复数才更新，否则可能是并发情况下被其他请求更新了，不处理
            timiForum.setReplyCount(replyCount);
            timiForum.setUpdateTime(new Date());
            timiForumMapper.updateById(timiForum);
        }
    }
}
