package com.timi.timizhuo.controller;

import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.service.TimiColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 卓依婷视频栏目controller
 */
@RestController
@RequestMapping(value = "/column")
public class TimiColumnController {

    private static Logger logger = LoggerFactory.getLogger(TimiColumnController.class);

    @Autowired
    private TimiColumnService timiColumnService;

    @PostMapping("/findAll")
    public ResponseData findAll(TimiColumnDto timiColumnDto) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiColumnDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else {
                List<TimiColumnDto> timiColumnDtoList = timiColumnService.findByColumnType(timiColumnDto);
                responseData.setData(timiColumnDtoList);
            }
        } catch (Exception e) {
            logger.error("m:register 视频栏目查询失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
