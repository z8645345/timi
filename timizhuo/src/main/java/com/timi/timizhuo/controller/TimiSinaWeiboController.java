package com.timi.timizhuo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.entity.TimiSinaWeibo;
import com.timi.timizhuo.service.TimiSinaWeiboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 卓依婷新浪微博controller
 */
@RestController
@RequestMapping(value = "/weibo")
public class TimiSinaWeiboController {

    private static Logger logger = LoggerFactory.getLogger(TimiSinaWeiboController.class);

    @Autowired
    private TimiSinaWeiboService timiSinaWeiboService;

    @PostMapping("/findAll")
    public ResponseData findAll(TimiSinaWeibo timiSinaWeibo) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiSinaWeibo == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            IPage<TimiSinaWeibo> result = timiSinaWeiboService.page(new Page<TimiSinaWeibo>().setCurrent(timiSinaWeibo.getPageNum()).setSize(timiSinaWeibo.getPageSize()).setDesc(timiSinaWeibo.getDesc()));
            responseData.setData(result);
        } catch (Exception e) {
            logger.error("m:register 查询新浪微博信息失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
