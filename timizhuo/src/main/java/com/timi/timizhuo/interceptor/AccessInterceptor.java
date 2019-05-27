package com.timi.timizhuo.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.timi.timizhuo.annotation.TimiLogin;
import com.timi.timizhuo.entity.TimiAccessLog;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.service.ITimiAccessLogService;
import com.timi.timizhuo.util.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class AccessInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, TimiUser> redisTemplate;
    @Resource
    private ITimiAccessLogService timiAccessLogService;

    /**
     * 前置方法：在handler方法执行之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (ObjectUtil.notEqual("/userMessage/findUserMessage", path)) {
            TimiAccessLog timiAccessLog = new TimiAccessLog();
            timiAccessLog.setApiName(request.getServletPath());
            timiAccessLog.setIp(IpUtils.getIpAddress(request));

            String token = request.getHeader("token");
            if (StringUtils.isNotEmpty(token)) {
                TimiUser timiUser = redisTemplate.boundValueOps("USER_TOKEN" + token).get();
                if (timiUser != null) {
                    timiAccessLog.setNickname(timiUser.getNickname());
                    timiAccessLog.setUsername(timiUser.getUsername());
                }
            }
            timiAccessLogService.save(timiAccessLog);
        }
        return true;
    }


}
