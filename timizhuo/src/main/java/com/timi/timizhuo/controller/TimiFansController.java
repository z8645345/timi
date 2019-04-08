package com.timi.timizhuo.controller;


import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.entity.TimiFans;
import com.timi.timizhuo.service.ITimiFansService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author timi
 * @since 2019-04-07
 */
@RestController
@RequestMapping("/timi/fans")
public class TimiFansController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(TimiUserController.class);
    @Autowired
    private ITimiFansService timiFansService;

    /**
     * 关注
     *
     * @param timiFans
     * @return
     */
    @PostMapping("/follow")
    public ResponseData follow(TimiFans timiFans) {
        ResponseData responseData = new ResponseData();
        try {
            timiFans.setUpdateTime(new Date());
            timiFans.setCreateTime(new Date());
            boolean save = timiFansService.save(timiFans);
            if (save) {
                responseData.setData(Constant.FOLLOW_USER_OK);
            } else {
                responseData.setFial();
                responseData.setMessage("婷迷关注失败");
            }
        } catch (Exception e) {
            logger.error("m:updateTimiUser 婷迷关注失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    /**
     * 关注列表传userId/粉丝列表传parentId
     *
     * @param timiFans
     * @return
     */
    @PostMapping("/followList")
    public ResponseData followList(TimiFans timiFans) {
        ResponseData responseData = new ResponseData();
        try {
            ServiceResponseData<List<TimiFans>> listServiceResponseData = timiFansService.selectByMap(timiFans);
            if (listServiceResponseData.isSuccess()) {
                responseData.setData(listServiceResponseData.getData());
            } else {
                responseData.setFial();
                responseData.setMessage("获取婷迷关注列表/粉丝列表失败");
            }
        } catch (Exception e) {
            logger.error("m:updateTimiUser 获取婷迷关注列表/粉丝列表失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
