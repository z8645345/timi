package com.timi.timizhuo.controller;

import com.ruijc.util.UUIDUtils;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.dto.QiniuUploadImgDto;
import com.timi.timizhuo.dto.TimiForumDto;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.util.QiniuUploadUtils;
import jodd.util.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
            String url = QiniuUploadUtils.imgUpload(qiniuUploadImgDto.getBase64(), QiniuUploadUtils.NamespaceEnums.getNamespaceByType(qiniuUploadImgDto.getNamespaceType()), UUIDUtils.uuid());
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
