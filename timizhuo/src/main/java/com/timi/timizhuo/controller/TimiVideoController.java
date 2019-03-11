package com.timi.timizhuo.controller;

import com.alibaba.druid.util.StringUtils;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.common.UserRocordEnum;
import com.timi.timizhuo.dao.model.TimiUserRecord;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.dto.TimiUserRecordDto;
import com.timi.timizhuo.dto.TimiVideoDto;
import com.timi.timizhuo.response.FindByColumnLimitResDTO;
import com.timi.timizhuo.service.TimiUserRecordService;
import com.timi.timizhuo.service.TimiVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 卓依婷视频controller
 */
@RestController
@RequestMapping(value = "/video")
public class TimiVideoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TimiVideoController.class);

    @Autowired
    private TimiVideoService timiVideoService;

    @Autowired
    private TimiUserRecordService timiUserRecordService;

    @PostMapping("/findByColumn")
    public ResponseData findByColumn(TimiVideoDto timiVideoDto, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiVideoDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else {
                List<TimiVideoDto> timiColumnDtoList = timiVideoService.findByColumn(timiVideoDto);
                setUserRecord(timiColumnDtoList, request);
                responseData.setData(timiColumnDtoList);
            }
        } catch (Exception e) {
            logger.error("m:register 根据栏目查询视频失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/randomVideo")
    public ResponseData randomVideo(TimiVideoDto timiVideoDto, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiVideoDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else {
                List<TimiVideoDto> timiColumnDtoList = timiVideoService.randomVideo(timiVideoDto);
                setUserRecord(timiColumnDtoList, request);
                responseData.setData(timiColumnDtoList);
            }
        } catch (Exception e) {
            logger.error("m:register 根据栏目查询视频失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/findById")
    public ResponseData findById(TimiVideoDto timiVideoDto, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiVideoDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else {
                TimiVideoDto resultTimiVideoDto = timiVideoService.findById(timiVideoDto);
                setUserRecord(resultTimiVideoDto, request);
                responseData.setData(resultTimiVideoDto);
            }
        } catch (Exception e) {
            logger.error("m:register 根据id查询视频失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/showVideo")
    public ResponseData showVideo(TimiVideoDto timiVideoDto) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiVideoDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiVideoDto.getId())) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            ServiceResponseData<Long> serviceResponseData = timiVideoService.showVideo(timiVideoDto);
            if (serviceResponseData.isSuccess()) {
                responseData.setData(serviceResponseData.getData());
            } else {
                responseData.setFial();
                responseData.setMessage(serviceResponseData.getMessage());
            }
        } catch (Exception e) {
            logger.error("m:showVideo 播放视频次数加1失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    private void setUserRecord(List<TimiVideoDto> timiColumnDtoList, HttpServletRequest request) {
        TimiUserDto timiUserDto = getLoginUser(request);
        if (timiUserDto != null) {
            List<TimiUserRecordDto> timiUserRecordDtoList = timiUserRecordService.findByUsername(timiUserDto);
            timiColumnDtoList.forEach(timiVideoDto -> timiUserRecordDtoList.forEach(timiUserRecordDto -> checkUserRecord(timiUserRecordDto, timiVideoDto)));
        }
    }

    private void setUserRecord(TimiVideoDto timiVideoDto, HttpServletRequest request) {
        TimiUserDto timiUserDto = getLoginUser(request);
        if (timiUserDto != null) {
            List<TimiUserRecordDto> timiUserRecordDtoList = timiUserRecordService.findByUsername(timiUserDto);
            timiUserRecordDtoList.forEach(timiUserRecordDto -> checkUserRecord(timiUserRecordDto, timiVideoDto));
        }
    }

    private void checkUserRecord(TimiUserRecordDto timiUserRecordDto, TimiVideoDto timiVideoDto) {
        if (timiUserRecordDto.getRecordId().equals(timiVideoDto.getId())) {
            if (timiUserRecordDto.getType().equals(UserRocordEnum.VIDOE_LOVE.getType())) {
                timiVideoDto.setUserLove(true);
            } else if (timiUserRecordDto.getType().equals(UserRocordEnum.VIDEO_COLLECTION.getType())) {
                timiVideoDto.setUserCollection(true);
            }
        }
    }

    @PostMapping("/findByColumnLimit")
    public ResponseData findByColumnLimit(TimiColumnDto timiColumnDto) {
        ResponseData responseData = new ResponseData();
        try {
            List<FindByColumnLimitResDTO> findByColumnLimitResDTOS = timiVideoService.findByColumnLimit(timiColumnDto);
            responseData.setData(findByColumnLimitResDTOS);
        } catch (Exception e) {
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
