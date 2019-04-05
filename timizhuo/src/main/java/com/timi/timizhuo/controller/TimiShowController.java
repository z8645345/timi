package com.timi.timizhuo.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.entity.TimiShow;
import com.timi.timizhuo.service.TimiShowService;
import com.timi.timizhuo.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 卓依婷最新演出活动controller
 */
@RestController
@RequestMapping(value = "/show")
public class TimiShowController {

    private static Logger logger = LoggerFactory.getLogger(TimiShowController.class);

    @Autowired
    private TimiShowService timiShowService;

    @PostMapping("/findAll")
    public String findAll(TimiShow timiShow) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiShow == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else {
                IPage<TimiShow> pageResult = timiShowService.page(timiShow.page());
                responseData.setData(pageResult);
            }
        } catch (Exception e) {
            logger.error("m:register 演出活动查询失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return JSONUtils.toJosnString(responseData, "yyyy年MM月dd");
    }

    @PostMapping("/findById")
    public String findById(TimiShow timiShow) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiShow == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else if (StringUtils.isEmpty(timiShow.getId())) {
                responseData.setFial();
                responseData.setMessage(Constant.SHOW_ID_NOT_NULL);
            } else {
                TimiShow result = timiShowService.getById(timiShow.getId());
                responseData.setData(result);
            }
        } catch (Exception e) {
            logger.error("m:register 根据id演出活动查询失败，showId=" + timiShow.getId(), e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return JSONUtils.toJosnString(responseData, "yyyy年MM月dd");
    }
}
