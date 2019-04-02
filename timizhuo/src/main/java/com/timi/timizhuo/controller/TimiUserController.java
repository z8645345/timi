package com.timi.timizhuo.controller;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dto.EmaillDto;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.service.TimiUserService;
import com.timi.timizhuo.util.EmaillUtils;
import com.timi.timizhuo.util.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
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
    public ResponseData sendEmall(TimiUserDto timiUserDto) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUserDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUserDto.getUsername())) {
                responseData.setFial();
                responseData.setMessage(Constant.USERNAME_NOT_NULL);
                return responseData;
            }
            TimiUserDto dto = timiUserService.findByUsername(timiUserDto.getUsername());
            if (dto != null) {
                responseData.setFial();
                responseData.setMessage(Constant.USERNAME_EXIST);
                return responseData;
            }
            dto = timiUserService.findByNikename(timiUserDto.getNickname());
            if (dto != null) {
                responseData.setFial();
                responseData.setMessage(Constant.NICKNAME_EXIST);
                return responseData;
            }
            String value = stringRedisTemplate.boundValueOps(CHECO_CODE_FIX + timiUserDto.getUsername()).get();
            if (StringUtil.isEmpty(value)) {
                EmaillDto emaillDto = new EmaillDto();
                emaillDto.setTo(timiUserDto.getUsername());
                emaillDto.setTitle("卓依婷官方粉丝团注册验证码");
                String checkCode = RandomUtils.digit6();
                emaillDto.setText("亲爱的timi，你的验证码是" + checkCode + ",有效期10分钟，欢迎加入卓依婷官方粉丝团");
                EmaillUtils.sendTextMail(emaillDto);
                stringRedisTemplate.boundValueOps(CHECO_CODE_FIX + timiUserDto.getUsername()).set(checkCode);
                stringRedisTemplate.boundValueOps(CHECO_CODE_FIX + timiUserDto.getUsername()).expire(10, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            logger.error("m:sendEmall 发送邮箱验证码失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/register")
    public ResponseData register(TimiUserDto timiUserDto) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUserDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUserDto.getUsername())) {
                responseData.setFial();
                responseData.setMessage(Constant.USERNAME_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUserDto.getPassword())) {
                responseData.setFial();
                responseData.setMessage(Constant.PASSWORD_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUserDto.getNickname())) {
                responseData.setFial();
                responseData.setMessage(Constant.NICKNAME_NOT_NULL);
                return responseData;
            }
            ServiceResponseData<TimiUserDto> serviceResponseData = timiUserService.register(timiUserDto);
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
    public ResponseData login(TimiUserDto timiUserDto) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUserDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUserDto.getUsername())) {
                responseData.setFial();
                responseData.setMessage(Constant.USERNAME_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiUserDto.getPassword())) {
                responseData.setFial();
                responseData.setMessage(Constant.PASSWORD_NOT_NULL);
                return responseData;
            }
            ServiceResponseData<TimiUserDto> serviceResponseData = timiUserService.login(timiUserDto);
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
            TimiUserDto timiUserDto = getLoginUser(request);
            if (timiUserDto != null) {
                responseData.setData(timiUserDto);
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

//    @PostMapping("/fileUpload")
//    public ResponseData fileUpload(String base64Str) {
//        base64Str = base64Str.substring(base64Str.indexOf(",")+1);
//        ResponseData responseData = new ResponseData();
//        String accessKey = "UpK4eYeeehxZYpW3Nt7zN0F5pjILYTExaSwFU73H";
//        String secretKey = "tL_yLv_mQUsxdRvla9F0K2MUnf9cffKEDhezOSBI";
//        String bucket = "timizhuo";
//
//        Configuration cfg = new Configuration(Zone.zone2());
//        UploadManager uploadManager = new UploadManager(cfg);
//
//        String key = UUID.randomUUID().toString() + ".jpg";
//        try {
//            byte[] uploadBytes = this.transformBase64(base64Str);
//            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
//            Auth auth = Auth.create(accessKey, secretKey);
//            String upToken = auth.uploadToken(bucket);
//            try {
//                Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
//                //解析上传成功的结果
//                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                responseData.setData("http://pkkwm1pvb.bkt.clouddn.com/" + putRet.key);
//                System.out.println(putRet.hash);
//            } catch (QiniuException ex) {
//                Response r = ex.response;
//                System.err.println(r.toString());
//                try {
//                    System.err.println(r.bodyString());
//                } catch (QiniuException ex2) {
//                    //ignore
//                }
//            }
//        } catch (Exception ex) {
//            responseData.setFial();
//            responseData.setMessage("图片上传失败");
//            return responseData;
//        }
//        responseData.setSuccess();
//        return responseData;
//    }

}
