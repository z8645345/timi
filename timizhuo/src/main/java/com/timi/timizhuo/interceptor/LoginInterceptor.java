package com.timi.timizhuo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.timi.timizhuo.annotation.TimiLogin;
import com.timi.timizhuo.entity.TimiUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    protected RedisTemplate<String, TimiUser> redisTemplate;

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

        // 免token注解
        TimiLogin timiLogin;
        //1： 判断是否方法级别的
        if (handler instanceof HandlerMethod) {
            timiLogin = ((HandlerMethod) handler).getMethodAnnotation(TimiLogin.class);
        } else {
            // 如果不是方法级别的
            return true;
        }
        if (timiLogin != null) {
            // 有注解就做登录校验
            String token = request.getHeader("token");
            TimiUser timiUser = redisTemplate.boundValueOps("USER_TOKEN" + token).get();
            if (timiUser == null) {
                print(response, "请重新登录!");
                return false;
            }
        }
        return true;
    }

    public void print(HttpServletResponse response, String message) {
        try {
            JSONObject result = new JSONObject();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            result.put("success", false);
            result.put("data", null);
            result.put("message", message);
            result.put("code", "404");
//            pw.println(result.toString());


            PrintWriter out = response.getWriter();
//            out.println("<script>");
//            out.println("alert('登陆失败!');");
//            out.println("parent.location.href='index.jsp';");
            out.write(JSONObject.toJSONString(result, SerializerFeature.WriteDateUseDateFormat));
            out.flush();
            out.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
