package com.timi.timizhuo.controller;

import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.entity.TimiUserRecord;
import com.timi.timizhuo.service.TimiUserRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 婷迷用户记录controller
 */
@RestController
@RequestMapping(value = "/user/record")
public class TimiUserRecordController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TimiUserRecordController.class);

    @Autowired
    private TimiUserRecordService timiUserRecordService;

    @PostMapping("/saveUserRecord")
    public ResponseData saveUserRecord(TimiUserRecord timiUserRecordDto, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUserRecordDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            TimiUser timiUser = getLoginUser(request);
            if (timiUser == null) {
                responseData.setFial();
                responseData.setMessage("请先登录");
                return responseData;
            }
            timiUserRecordDto.setUsername(timiUser.getUsername());
            ServiceResponseData<String> serviceResponseData = timiUserRecordService.saveUserRecord(timiUserRecordDto);
            if (!serviceResponseData.isSuccess()) {
                responseData.setFial();
                responseData.setMessage(serviceResponseData.getMessage());
            }
        } catch (Exception e) {
            logger.error("m:saveUserRecord 用户记录操作失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
