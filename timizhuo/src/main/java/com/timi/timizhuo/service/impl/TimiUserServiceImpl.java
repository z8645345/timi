package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.mapper.TimiUserMapper;
import com.timi.timizhuo.service.TimiUserService;
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
public class TimiUserServiceImpl extends ServiceImpl<TimiUserMapper, TimiUser> implements TimiUserService {

    @Autowired
    private TimiUserMapper timiUserMapper;

    @Override
    public ServiceResponseData<TimiUser> register(TimiUser timiUser) throws Exception {
        ServiceResponseData<TimiUser> serviceResponseData = new ServiceResponseData<>();
        TimiUser result = timiUserMapper.selectOne(new QueryWrapper<TimiUser>().eq("username", timiUser.getUsername()));
        if (result != null) {
            serviceResponseData.setFail();
            serviceResponseData.setMessage(Constant.USERNAME_EXIST);
            return serviceResponseData;
        }
        result = timiUserMapper.selectOne(new QueryWrapper<TimiUser>().eq("nickname", timiUser.getNickname()));
        if (result != null) {
            serviceResponseData.setFail();
            serviceResponseData.setMessage(Constant.NICKNAME_EXIST);
            return serviceResponseData;
        }
        String base64Str = timiUser.getHeaderImage().substring(timiUser.getHeaderImage().indexOf(",")+1);
        String imgUrl = QiniuUploadUtils.imgUpload(base64Str, QiniuUploadUtils.NamespaceEnums.USER_PIC, timiUser.getUsername());
        timiUser.setPic(imgUrl);
        timiUser.setPassword(Md5Utils.encoderByMd5(timiUser.getPassword()));
        timiUserMapper.insert(timiUser);
        serviceResponseData.setData(timiUser);
        serviceResponseData.setSuccess();
        return serviceResponseData;
    }

    @Override
    public ServiceResponseData<TimiUser> login(TimiUser timiUser) throws Exception {
        ServiceResponseData<TimiUser> serviceResponseData = new ServiceResponseData<>();
        timiUser.setPassword(Md5Utils.encoderByMd5(timiUser.getPassword()));
        TimiUser result = timiUserMapper.selectOne(new QueryWrapper<TimiUser>().eq("username", timiUser.getUsername()).eq("password", timiUser.getPassword()));
        if (result == null) {
            serviceResponseData.setFail();
            serviceResponseData.setMessage(Constant.USERNAME_OR_PASSWORD_ERROR);
            return serviceResponseData;
        }
        serviceResponseData.setSuccess();
        serviceResponseData.setData(result);
        return serviceResponseData;
    }
    @Override
    public ServiceResponseData<TimiUser> updateTimiUser(TimiUser timiUser) throws Exception {
        ServiceResponseData<TimiUser> serviceResponseData = new ServiceResponseData<>();
        timiUser.setPassword(Md5Utils.encoderByMd5(timiUser.getPassword()));
        int i = timiUserMapper.updateById(timiUser);
        if (i == 0) {
            serviceResponseData.setFail();
            serviceResponseData.setMessage(Constant.UPDATE_USER_FAIL);
            return serviceResponseData;
        }
        serviceResponseData.setSuccess();
        serviceResponseData.setData(null);
        return serviceResponseData;
    }
}
