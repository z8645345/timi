package com.timi.timizhuo.controller;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.dto.TimiImagesDto;
import com.timi.timizhuo.dto.TimiShowDto;
import com.timi.timizhuo.dto.TimiVideoDto;
import com.timi.timizhuo.response.FindByColumnLimitResDTO;
import com.timi.timizhuo.service.TimiColumnService;
import com.timi.timizhuo.service.TimiImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 卓依婷图片controller
 */
@RestController
@RequestMapping(value = "/images")
public class TimiImagesController {

    private static Logger logger = LoggerFactory.getLogger(TimiImagesController.class);

    @Autowired
    private TimiImagesService timiImagesService;

    @GetMapping("/syncweibo")
    public ResponseData syncweibo() {
        ResponseData responseData = new ResponseData();
        try {
            timiImagesService.syncWeiboImages();
        } catch (Exception e) {
            logger.error("m:syncweibo 同步微博图片失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/findByColumn")
    public ResponseData findByColumn(TimiImagesDto timiImagesDto) {
        ResponseData responseData = new ResponseData();
        try {
            PageInfo<TimiImagesDto> pageInfo = timiImagesService.findByColumn(timiImagesDto);
            responseData.setData(pageInfo);
        } catch (Exception e) {
            logger.error("m:findByColumn 根据栏目查询图片失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/findByColumnLimit")
    public ResponseData findByColumnLimit(TimiColumnDto timiColumnDto) {
        ResponseData responseData = new ResponseData();
        try {
            List<FindByColumnLimitResDTO> findByColumnLimitResDTOS = timiImagesService.findByColumnLimit(timiColumnDto);
            responseData.setData(findByColumnLimitResDTOS);
        } catch (Exception e) {
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
