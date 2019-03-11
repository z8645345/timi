package com.timi.timizhuo.service.impl;

import com.github.pagehelper.PageHelper;
import com.timi.timizhuo.dao.mapper.TimiMsgLogMapper;
import com.timi.timizhuo.dao.model.TimiMsgLog;
import com.timi.timizhuo.dto.TimiMsgLogDTO;
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
public class TimiMsgLogServiceImpl implements TimiMsgLogService {

    @Autowired
    private TimiMsgLogMapper timiMsgLogMapper;

    @Override
    public List<TimiMsgLogDTO> listAll(TimiMsgLogDTO timiMsgLogDTO) {
        PageHelper.startPage(timiMsgLogDTO.getPageNum(), timiMsgLogDTO.getPageSize());
        List<TimiMsgLog> timiMsgLogList = timiMsgLogMapper.findAll();
        List<TimiMsgLogDTO> timiMsgLogDTOList = BeanConvertUtils.convertList(timiMsgLogList, TimiMsgLogDTO.class);
        timiMsgLogDTOList.forEach(e -> e.setTimestamp(e.getCreateTime().getTime()));
        return timiMsgLogDTOList;
    }
}
