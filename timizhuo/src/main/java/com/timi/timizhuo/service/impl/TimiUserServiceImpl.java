package com.timi.timizhuo.service.impl;

import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.mapper.TimiUserMapper;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.service.TimiUserService;
import com.timi.timizhuo.util.BeanConvertUtils;
import com.timi.timizhuo.util.Md5Utils;
import com.timi.timizhuo.util.QiniuUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String imgUrl = QiniuUploadUtils.imgUpload(base64Str, QiniuUploadUtils.NamespaceEnums.USER_PIC, timiUserDto.getUsername());
        timiUserDto.setPic(imgUrl);
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
}
