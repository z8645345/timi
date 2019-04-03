package com.timi.timizhuo.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.StringUtil;
import com.qiniu.util.Auth;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dto.request.EmaillDto;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.service.TimiUserService;
import com.timi.timizhuo.util.EmaillUtils;
import com.timi.timizhuo.util.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description 用户controller
 * @Auther timi
 * @Date 2018/12/6 000612:34
 */
@RestController
@RequestMapping(value = "/user")
public class TimiUserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TimiUserController.class);

    private static final String CHECO_CODE_FIX = "CHECO_CODE_FIX";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TimiUserService timiUserService;

    @PostMapping("/sendEmall")
    public ResponseData sendEmall(TimiUser timiUser) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUser == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUser.getUsername())) {
                responseData.setFial();
                responseData.setMessage(Constant.USERNAME_NOT_NULL);
                return responseData;
            }
            TimiUser dto = timiUserService.getOne(new QueryWrapper<TimiUser>().eq("username", timiUser.getUsername()));
            if (dto != null) {
                responseData.setFial();
                responseData.setMessage(Constant.USERNAME_EXIST);
                return responseData;
            }
            dto = timiUserService.getOne(new QueryWrapper<TimiUser>().eq("nickname", timiUser.getNickname()));
            if (dto != null) {
                responseData.setFial();
                responseData.setMessage(Constant.NICKNAME_EXIST);
                return responseData;
            }
            String value = stringRedisTemplate.boundValueOps(CHECO_CODE_FIX + timiUser.getUsername()).get();
            if (StringUtil.isEmpty(value)) {
                EmaillDto emaillDto = new EmaillDto();
                emaillDto.setTo(timiUser.getUsername());
                emaillDto.setTitle("卓依婷官方粉丝团注册验证码");
                String checkCode = RandomUtils.digit6();
                emaillDto.setText("亲爱的timi，你的验证码是" + checkCode + ",有效期10分钟，欢迎加入卓依婷官方粉丝团");
                EmaillUtils.sendTextMail(emaillDto);
                stringRedisTemplate.boundValueOps(CHECO_CODE_FIX + timiUser.getUsername()).set(checkCode);
                stringRedisTemplate.boundValueOps(CHECO_CODE_FIX + timiUser.getUsername()).expire(10, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            logger.error("m:sendEmall 发送邮箱验证码失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/register")
    public ResponseData register(TimiUser timiUser) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUser == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUser.getUsername())) {
                responseData.setFial();
                responseData.setMessage(Constant.USERNAME_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUser.getPassword())) {
                responseData.setFial();
                responseData.setMessage(Constant.PASSWORD_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUser.getNickname())) {
                responseData.setFial();
                responseData.setMessage(Constant.NICKNAME_NOT_NULL);
                return responseData;
            }
            ServiceResponseData<TimiUser> serviceResponseData = timiUserService.register(timiUser);
            if (serviceResponseData.isSuccess()) {
                responseData.setData(serviceResponseData.getData());
            } else {
                responseData.setFial();
                responseData.setMessage(serviceResponseData.getMessage());
            }
        } catch (Exception e) {
            logger.error("m:register 婷迷会员注册失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/login")
    public ResponseData login(TimiUser timiUser) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUser == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUser.getUsername())) {
                responseData.setFial();
                responseData.setMessage(Constant.USERNAME_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUser.getPassword())) {
                responseData.setFial();
                responseData.setMessage(Constant.PASSWORD_NOT_NULL);
                return responseData;
            }
            ServiceResponseData<TimiUser> serviceResponseData = timiUserService.login(timiUser);
            if (serviceResponseData.isSuccess()) {
                String token = UUID.randomUUID().toString();
                redisTemplate.boundValueOps("USER_TOKEN" + token).set(serviceResponseData.getData());
                redisTemplate.boundValueOps("USER_TOKEN" + token).expire(15, TimeUnit.DAYS);
                serviceResponseData.getData().setToken(token);
                responseData.setData(serviceResponseData.getData());
            } else {
                responseData.setFial();
                responseData.setMessage(serviceResponseData.getMessage());
            }
        } catch (Exception e) {
            logger.error("m:login 婷迷会员登录失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/logout")
    public ResponseData logout(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String token = request.getHeader("token");
        redisTemplate.delete("USER_TOKEN" + token);
        responseData.setSuccess();
        return responseData;
    }

    @PostMapping("/isLogin")
    public ResponseData isLogin(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            TimiUser timiUser = getLoginUser(request);
            if (timiUser != null) {
                responseData.setData(timiUser);
            } else {
                responseData.setFial();
            }
        } catch (Exception e) {
            logger.error("m:login 查询婷迷会员登录状态失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/getQiniuToken")
    public ResponseData getQiniuToken(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String accessKey = "UpK4eYeeehxZYpW3Nt7zN0F5pjILYTExaSwFU73H";
        String secretKey = "tL_yLv_mQUsxdRvla9F0K2MUnf9cffKEDhezOSBI";
        String bucket = "timizhuo";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        responseData.setData(upToken);
        responseData.setSuccess();
        return responseData;
    }

}
