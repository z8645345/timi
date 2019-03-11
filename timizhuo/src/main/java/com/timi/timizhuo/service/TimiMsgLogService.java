package com.timi.timizhuo.service;

import com.timi.timizhuo.dto.TimiMsgLogDTO;

import java.util.List;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/8 0008
 */
public interface TimiMsgLogService {

    List<TimiMsgLogDTO> listAll(TimiMsgLogDTO timiMsgLogDTO);
}
