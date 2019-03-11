package com.timi.timizhuo.controller;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.dto.TimiSinaWeiboDto;
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
    public ResponseData findAll(TimiSinaWeiboDto timiSinaWeiboDto) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiSinaWeiboDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            PageInfo<TimiSinaWeiboDto> pageInfo = timiSinaWeiboService.findAllByPage(timiSinaWeiboDto);
            responseData.setData(pageInfo);
        } catch (Exception e) {
            logger.error("m:register 查询新浪微博信息失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
