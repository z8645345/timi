package com.timi.timizhuo.controller;

import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.dto.request.QiniuUploadImgDto;
import com.timi.timizhuo.util.QiniuUploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description 对七牛云操作的controller
 * @Auther timi
 * @Date 2019/3/19 0019
 */
@RestController
@RequestMapping("/qiniu")
public class QiniuController extends BaseController {

    @PostMapping("/uploadImg")
    public ResponseData uploadImg(QiniuUploadImgDto qiniuUploadImgDto) {
        ResponseData responseData = new ResponseData();
        try {
            if (StringUtils.isEmpty(qiniuUploadImgDto.getImgName())) {
                qiniuUploadImgDto.setImgName(UUID.randomUUID().toString());
            }
            String url = QiniuUploadUtils.imgUpload(qiniuUploadImgDto.getBase64(), QiniuUploadUtils.NamespaceEnums.getNamespaceByType(qiniuUploadImgDto.getNamespaceType()), qiniuUploadImgDto.getImgName());
            responseData.setData(url);
            responseData.setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
