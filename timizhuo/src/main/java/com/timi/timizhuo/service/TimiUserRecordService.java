package com.timi.timizhuo.service;

import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.dto.TimiUserRecordDto;

import java.util.List;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/17 001712:56
 */
public interface TimiUserRecordService {
    ServiceResponseData<String> saveUserRecord(TimiUserRecordDto timiUserRecordDto);

    List<TimiUserRecordDto> findByUsername(TimiUserDto timiUserDto);
}
