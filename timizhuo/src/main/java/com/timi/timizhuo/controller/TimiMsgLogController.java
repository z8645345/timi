package com.timi.timizhuo.controller;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.dto.TimiMsgLogDTO;
import com.timi.timizhuo.dto.TimiShowDto;
import com.timi.timizhuo.dto.TimiSinaWeiboDto;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.service.TimiMsgLogService;
import com.timi.timizhuo.util.SpringUtil;
import com.timi.timizhuo.websocket.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2019/1/8 0008
 */
@RestController
@RequestMapping(value = "/msgLog")
public class TimiMsgLogController {

    @Autowired
    private TimiMsgLogService timiMsgLogService;

    @PostMapping("/findAll")
    public ResponseData findAll(TimiMsgLogDTO timiMsgLogDTO) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiMsgLogDTO == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            List<TimiMsgLogDTO> timiMsgLogDTOList = timiMsgLogService.listAll(timiMsgLogDTO);
//            Collections.sort(timiMsgLogDTOList, (o1, o2) -> (int) (o1.getCreateTime().getTime() - o2.getCreateTime().getTime()));

            responseData.setData(timiMsgLogDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/getOnlineUser")
    public ResponseData getOnlineUser() {
        ResponseData responseData = new ResponseData();
        try {
            responseData.setData(SpringUtil.getBean("redisTemplate", RedisTemplate.class).boundHashOps(Const.ONLINE_USER).values());
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
