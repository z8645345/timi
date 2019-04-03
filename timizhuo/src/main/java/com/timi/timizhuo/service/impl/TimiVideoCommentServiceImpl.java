package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiVideoCommentDto;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.entity.TimiVideoComment;
import com.timi.timizhuo.mapper.TimiUserMapper;
import com.timi.timizhuo.mapper.TimiVideoCommentMapper;
import com.timi.timizhuo.service.TimiVideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/19 001907:28
 */
@Service
public class TimiVideoCommentServiceImpl extends ServiceImpl<TimiVideoCommentMapper, TimiVideoComment> implements TimiVideoCommentService {

}
