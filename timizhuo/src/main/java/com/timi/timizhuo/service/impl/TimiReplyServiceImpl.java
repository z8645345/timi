package com.timi.timizhuo.service.impl;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.enums.ReplyEnum;
import com.timi.timizhuo.entity.TimiReply;
import com.timi.timizhuo.mapper.TimiReplyMapper;
import com.timi.timizhuo.service.TimiReplyService;
import com.timi.timizhuo.util.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TimiReplyServiceImpl implements TimiReplyService {

    @Resource
    private TimiReplyMapper timiReplyMapper;

    @Override
    @Transactional
    public boolean addForum(TimiReply timiReply) {
        if (StringUtils.isBlank(timiReply.getForumId())) {
            log.warn("数据有误,forumId为空");
            return false;
        }
        //主贴回复 ---  回复id会空
        if (timiReply.getReplyType().equals(ReplyEnum.ReplyTypeEnum.MAIN.getType())) {
            //根据主贴id查询
            List<TimiReply> timiReplies = this.timiReplyMapper.findByForumId(timiReply.getForumId());
            if (CollectionUtils.isEmpty(timiReplies)) {
                //如果没有回复过则为第一次回复 有楼层标识
                timiReply.setTierNum(1);
                timiReply.setCreateTime(new Date());
                timiReply.setUpdateTime(timiReply.getCreateTime());
                timiReply.setReplyTime(timiReply.getCreateTime());
                return this.timiReplyMapper.insert(timiReply) == 1;
            } else {
                //按回复时间倒叙查询的数据
                TimiReply timiReplyTmp = timiReplies.get(0);
                Integer tierNum = timiReplyTmp.getTierNum();
                Integer i = ++tierNum;
                timiReply.setTierNum(i);
                timiReply.setCreateTime(new Date());
                timiReply.setUpdateTime(timiReply.getCreateTime());
                timiReply.setReplyTime(timiReply.getCreateTime());
                return this.timiReplyMapper.insert(timiReply) == 1;
            }
        } else if (timiReply.getReplyType().equals(ReplyEnum.ReplyTypeEnum.TIER.getType())) {
            // 楼层回复
            timiReply.setCreateTime(new Date());
            timiReply.setUpdateTime(timiReply.getCreateTime());
            timiReply.setReplyTime(timiReply.getCreateTime());
            BeanConvertUtils.convert(timiReply, timiReply);
            this.timiReplyMapper.insert(timiReply);
        } else if (timiReply.getReplyType().equals(ReplyEnum.ReplyTypeEnum.SON.getType())) {
            //子回复回复
            //修改楼层回复的回复数
            timiReply.setCreateTime(new Date());
            timiReply.setUpdateTime(timiReply.getCreateTime());
            timiReply.setReplyTime(timiReply.getCreateTime());
            BeanConvertUtils.convert(timiReply, timiReply);
            this.timiReplyMapper.insert(timiReply);
            //1 先查询主回复数据
            TimiReply replyById = this.timiReplyMapper.selectById(timiReply.getParentId());
            if (replyById == null) {
                log.warn("数据有误,根据id查询不到数据");
                return false;
            }
            //2 修改主回复数
            TimiReply upReply = new TimiReply();
            upReply.setReplyId(timiReply.getParentId());
            upReply.setUpdateTime(new Date());
            Integer replyNum = replyById.getReplyNum();
            if (replyNum == null){
                upReply.setReplyNum(1);
            }else {
                Integer num = ++replyNum;
                upReply.setReplyNum(num);
            }
            this.timiReplyMapper.updateReplyNum(upReply);
        }

        return true;
    }

    @Override
    public List<TimiReply> findForumByStick() {
        return null;
    }

    @Override
    public PageInfo<TimiReply> findPage(TimiReply timiReplyDto) {
        if (timiReplyDto == null || StringUtils.isBlank(timiReplyDto.getForumId())) return null;
        //查询所有主id的所有数据
        List<TimiReply> timiReplyList = this.timiReplyMapper.findByForumId(timiReplyDto.getForumId());
        if (CollectionUtils.isEmpty(timiReplyList)) {
            log.warn("查询数据为空");
            return null;
        }
        List<TimiReply> result = new ArrayList<>();
        for (TimiReply timiReply : timiReplyList) {
            result.add(timiReply);
            this.getTreeReplyList(result, timiReply.getParentId());
        }
        return null;
    }

    private List<TimiReply> getTreeReplyList(List<TimiReply> result, String parentId) {
        if (StringUtils.isNotBlank(parentId)) {
            TimiReply queryReply = new TimiReply();
            queryReply.setParentId(parentId);
            List<TimiReply> byCondition = this.timiReplyMapper.findByCondition(queryReply);
            if (!CollectionUtils.isEmpty(byCondition)) {
                for (TimiReply timiReply : byCondition) {
                    result.add(timiReply);
                    getTreeReplyList(result, timiReply.getParentId());
                }
            }
        }
        return result;
    }

}