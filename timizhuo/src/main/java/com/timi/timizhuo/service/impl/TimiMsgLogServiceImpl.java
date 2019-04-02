package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.timi.timizhuo.dto.TimiMsgLogDTO;
import com.timi.timizhuo.entity.TimiMsgLog;
import com.timi.timizhuo.mapper.TimiMsgLogMapper;
import com.timi.timizhuo.service.TimiMsgLogService;
import com.timi.timizhuo.util.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/8 0008
 */
@Service("timiMsgLogService")
public class TimiMsgLogServiceImpl extends ServiceImpl<TimiMsgLogMapper, TimiMsgLog> implements TimiMsgLogService {

}
