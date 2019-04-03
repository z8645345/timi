package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.entity.TimiUserRecord;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/17 001712:56
 */
public interface TimiUserRecordService extends IService<TimiUserRecord> {
    ServiceResponseData<String> saveUserRecord(TimiUserRecord timiUserRecordDto);
}
