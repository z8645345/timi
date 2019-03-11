package com.timi.timizhuo.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dao.mapper.TimiUserMapper;
import com.timi.timizhuo.dao.model.TimiUser;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.service.TimiUserService;
import com.timi.timizhuo.util.BeanConvertUtils;
import com.timi.timizhuo.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description 婷迷用户service实现类
 * @Auther timi
 * @Date 2018/12/6 000619:40
 */
@Service
public class TimiUserServiceImpl implements TimiUserService {

    @Autowired
    private TimiUserMapper timiUserMapper;

    @Override
    public ServiceResponseData<TimiUserDto> register(TimiUserDto timiUserDto) throws Exception {
        ServiceResponseData<TimiUserDto> serviceResponseData = new ServiceResponseData<>();
        TimiUser result = timiUserMapper.selectByUsername(timiUserDto.getUsername());
        if (result != null) {
            serviceResponseData.setFail();
            serviceResponseData.setMessage(Constant.USERNAME_EXIST);
            return serviceResponseData;
        }
        result = timiUserMapper.selectByNickname(timiUserDto.getNickname());
        if (result != null) {
            serviceResponseData.setFail();
            serviceResponseData.setMessage(Constant.NICKNAME_EXIST);
            return serviceResponseData;
        }
        String base64Str = timiUserDto.getHeaderImage().substring(timiUserDto.getHeaderImage().indexOf(",")+1);
        String accessKey = "UpK4eYeeehxZYpW3Nt7zN0F5pjILYTExaSwFU73H";
        String secretKey = "tL_yLv_mQUsxdRvla9F0K2MUnf9cffKEDhezOSBI";
        String bucket = "timizhuo";

        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);

        String key = UUID.randomUUID().toString() + ".jpg";
        byte[] uploadBytes = this.transformBase64(base64Str);
        ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        timiUserDto.setPic("http://pkkwm1pvb.bkt.clouddn.com/" + putRet.key);
        timiUserDto.setPassword(Md5Utils.encoderByMd5(timiUserDto.getPassword()));
        TimiUser timiUser = new TimiUser();
        BeanConvertUtils.convert(timiUserDto, timiUser);
        timiUserMapper.insert(timiUser);
        serviceResponseData.setData(timiUserDto);
        serviceResponseData.setSuccess();
        return serviceResponseData;
    }

    @Override
    public TimiUserDto findByUsername(String username) {
        TimiUser timiUser = timiUserMapper.selectByUsername(username);
        if (timiUser != null) {
            TimiUserDto timiUserDto = new TimiUserDto();
            BeanConvertUtils.convert(timiUser, timiUserDto);
            return timiUserDto;
        }
        return null;
    }

    @Override
    public TimiUserDto findByNikename(String nickname) {
        TimiUser timiUser = timiUserMapper.selectByNickname(nickname);
        if (timiUser != null) {
            TimiUserDto timiUserDto = new TimiUserDto();
            BeanConvertUtils.convert(timiUser, timiUserDto);
            return timiUserDto;
        }
        return null;
    }

    @Override
    public ServiceResponseData<TimiUserDto> login(TimiUserDto timiUserDto) throws Exception {
        ServiceResponseData<TimiUserDto> serviceResponseData = new ServiceResponseData<>();
        timiUserDto.setPassword(Md5Utils.encoderByMd5(timiUserDto.getPassword()));
        TimiUser timiUser = new TimiUser();
        BeanConvertUtils.convert(timiUserDto, timiUser);
        TimiUser result = timiUserMapper.selectByUsernameAndPassword(timiUser);
        if (result == null) {
            serviceResponseData.setFail();
            serviceResponseData.setMessage(Constant.USERNAME_OR_PASSWORD_ERROR);
            return serviceResponseData;
        }
        TimiUserDto resultDto = new TimiUserDto();
        BeanConvertUtils.convert(result, resultDto);
        serviceResponseData.setSuccess();
        serviceResponseData.setData(resultDto);
        return serviceResponseData;
    }

    private byte[] transformBase64(String str) {
        BASE64Decoder decode = new BASE64Decoder();
        byte[] b = null;
        try {
            b = decode.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}
