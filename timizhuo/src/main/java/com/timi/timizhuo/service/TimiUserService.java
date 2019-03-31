package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.entity.TimiUser;

/**
 * @Description 婷迷用户service
 * @Auther timi
 * @Date 2018/12/6 0006 19:40
 */
public interface TimiUserService extends IService<TimiUser> {
    ServiceResponseData<TimiUserDto> register(TimiUserDto timiUserDto) throws Exception;

    TimiUserDto findByUsername(String username);

    TimiUserDto findByNikename(String nickname);

    ServiceResponseData<TimiUserDto> login(TimiUserDto timiUserDto) throws Exception;
}
