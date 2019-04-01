package com.timi.timizhuo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.entity.TimiColumn;
import com.timi.timizhuo.entity.TimiImages;
import com.timi.timizhuo.response.FindByColumnLimitResDTO;
import com.timi.timizhuo.service.TimiImagesService;
import org.apache.commons.lang3.StringUtils;
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
    public ResponseData findByColumn(TimiImages timiImages) {
        ResponseData responseData = new ResponseData();
        try {
            Page<TimiImages> page = new Page<>();
            page.setCurrent(timiImages.getPageNum());
            page.setSize(timiImages.getPageSize());
            page.setDesc("create_time");
            QueryWrapper<TimiImages> wrapper = null;
            if (StringUtils.isNotEmpty(timiImages.getColumnNo())) {
                wrapper = new QueryWrapper<TimiImages>().eq("column_no", timiImages.getColumnNo());
            }
            IPage<TimiImages> result = timiImagesService.page(page, wrapper);
            responseData.setData(result);
        } catch (Exception e) {
            logger.error("m:findByColumn 根据栏目查询图片失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/findByColumnLimit")
    public ResponseData findByColumnLimit(TimiColumn timiColumn) {
        ResponseData responseData = new ResponseData();
        try {
            List<FindByColumnLimitResDTO> findByColumnLimitResDTOS = timiImagesService.findByColumnLimit(timiColumn);
            responseData.setData(findByColumnLimitResDTOS);
        } catch (Exception e) {
            logger.error(Constant.SYSTEM_ERROR,e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
