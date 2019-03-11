package com.timi.timizhuo.controller;

import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.dto.TimiUserRecordDto;
import com.timi.timizhuo.service.TimiColumnService;
import com.timi.timizhuo.service.TimiUserRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

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
    public ResponseData saveUserRecord(TimiUserRecordDto timiUserRecordDto, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUserRecordDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            TimiUserDto timiUserDto = getLoginUser(request);
            if (timiUserDto == null) {
                responseData.setFial();
                responseData.setMessage("请先登录");
                return responseData;
            }
            timiUserRecordDto.setUsername(timiUserDto.getUsername());
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
