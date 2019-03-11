package com.timi.timizhuo.service;

import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dto.TimiUserDto;

/**
 * @Description 婷迷用户service
 * @Auther timi
 * @Date 2018/12/6 0006 19:40
 */
public interface TimiUserService {
    ServiceResponseData<TimiUserDto> register(TimiUserDto timiUserDto) throws Exception;

    TimiUserDto findByUsername(String username);

    TimiUserDto findByNikename(String nickname);

    ServiceResponseData<TimiUserDto> login(TimiUserDto timiUserDto) throws Exception;
}
