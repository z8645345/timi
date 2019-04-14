package com.timi.timizhuo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.timi.timizhuo.annotation.TimiLogin;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.entity.TimiFans;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.service.ITimiFansService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/fans")
public class TimiFansController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(TimiUserController.class);
    @Autowired
    private ITimiFansService timiFansService;

    /**
     * 当前登录用户是否关注指定用户
     * @param request
     * @param timiFans
     * @return
     */
    @PostMapping("/isFollow")
    @TimiLogin
    public ResponseData isFollow(HttpServletRequest request, TimiFans timiFans) {
        ResponseData responseData = new ResponseData();
        try {
            if (StringUtils.isEmpty(timiFans.getParentId())) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            timiFans.setUserId(getLoginUser(request).getId());
            responseData.setData(timiFansService.getOne(new QueryWrapper<>(timiFans)) != null);
        } catch (Exception e) {
            logger.error("m:isFollow 查询是否关注失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    /**
     * 关注
     *
     * @param timiFans
     * @return
     */
    @PostMapping("/follow")
    @TimiLogin
    public ResponseData follow(HttpServletRequest request, TimiFans timiFans) {
        ResponseData responseData = new ResponseData();
        try {
            if (StringUtils.isEmpty(timiFans.getParentId())) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            timiFans.setUserId(getLoginUser(request).getId());
            if (timiFansService.getOne(new QueryWrapper<>(timiFans)) == null) {
                timiFans.setUpdateTime(new Date());
                timiFans.setCreateTime(new Date());
                boolean save = timiFansService.save(timiFans);
                if (save) {
                    responseData.setData(Constant.FOLLOW_USER_OK);
                } else {
                    responseData.setFial();
                    responseData.setMessage("婷迷关注失败");
                }
            }
            responseData.setData(Constant.FOLLOW_USER_OK);
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
    @TimiLogin
    public ResponseData followList(HttpServletRequest request, TimiFans timiFans) {
        ResponseData responseData = new ResponseData();
        try {
            if(timiFans==null){
                timiFans = new TimiFans();
            }
            TimiUser timiUser = getLoginUser(request);
            if (timiUser != null) {
                timiFans.setUserId(timiUser.getId());
            }
            List<TimiFans> result = timiFansService.list(new QueryWrapper<>(timiFans));
            responseData.setSuccess();
            responseData.setData(result);
        } catch (Exception e) {
            logger.error("m:updateTimiUser 获取婷迷关注列表/粉丝列表失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
