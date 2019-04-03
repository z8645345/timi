package com.timi.timizhuo.controller;

import com.timi.timizhuo.entity.TimiUser;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/17 001720:35
 */
public class BaseController {

    @Resource
    protected RedisTemplate<String, TimiUser> redisTemplate;

    protected TimiUser  getLoginUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        TimiUser timiUser = redisTemplate.boundValueOps("USER_TOKEN" + token).get();
        return timiUser;
    }
}
