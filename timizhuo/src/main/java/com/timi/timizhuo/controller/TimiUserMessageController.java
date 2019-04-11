package com.timi.timizhuo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.timi.timizhuo.annotation.TimiLogin;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.entity.TimiUserMessage;
import com.timi.timizhuo.enums.UserMessageEnum;
import com.timi.timizhuo.service.TimiUserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author timi
 * @since 2019-04-08
 */
@RestController
@RequestMapping("/userMessage")
@Slf4j
public class TimiUserMessageController extends BaseController {


    @Autowired
    private TimiUserMessageService timiUserMessageService;

    /**
     * 根据当前用户消息
     *
     * @return
     */
    @PostMapping("/findUserMessage")
    @TimiLogin
    public ResponseData findUserMessage(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            TimiUser timiUser = getLoginUser(request);
            if (timiUser == null) {
                responseData.setFial();
                responseData.setMessage(Constant.FORUM_USER_NOT_LOGIN);
                return responseData;
            }
            TimiUserMessage timiUserMessage = new TimiUserMessage();
            timiUserMessage.setUserId(timiUser.getId());
            timiUserMessage.setMessageState(UserMessageEnum.MessageStateEnum.UNREAD.getValue());
            List<TimiUserMessage> list = this.timiUserMessageService.list(new QueryWrapper<>(timiUserMessage));
            responseData.setData(list);
            responseData.setSuccess();
        } catch (Exception e) {
            log.error("userMessage findUserMessage error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    /**
     * 修改为已读
     *
     * @param timiUserMessage
     * @return
     */
    @PostMapping("/upReadState")
    @TimiLogin
    public ResponseData upUserMessage(TimiUserMessage timiUserMessage) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiUserMessage == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isBlank(timiUserMessage.getId())) {
                responseData.setFial();
                responseData.setMessage(Constant.ID_NOT_NULL);
                return responseData;
            }
            timiUserMessage.setMessageState(UserMessageEnum.MessageStateEnum.READ.getValue());
            boolean b = this.timiUserMessageService.updateById(timiUserMessage);
            if (b) {
                responseData.setSuccess();
            } else {
                responseData.setFial();
            }
        } catch (Exception e) {
            log.error("userMessage upReadState error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

}
