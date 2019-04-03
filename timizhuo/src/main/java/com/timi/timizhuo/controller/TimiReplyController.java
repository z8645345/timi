package com.timi.timizhuo.controller;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.dto.TimiForumDto;
import com.timi.timizhuo.dto.TimiReplyDto;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.service.TimiForumService;
import com.timi.timizhuo.service.TimiReplyService;
import com.timi.timizhuo.util.JSONUtils;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 回复
 * @author  Cruisin
 */
@RestController
@RequestMapping("/reply")
@Slf4j
public class TimiReplyController extends  BaseController{

    @Autowired
    private TimiReplyService timiReplyService;

    /**
     * 发帖
     * @param timiReplyDto
     * @param request
     * @return
     */
    @PostMapping("/addReply")
    public ResponseData addReply(@RequestBody TimiReplyDto timiReplyDto , HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        log.info("Reply  addReply  timiForumDto :{}",timiReplyDto );
        try {
            if (timiReplyDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtil.isBlank(timiReplyDto.getReplyCotent())) {
                responseData.setFial();
                responseData.setMessage(Constant.CONTENT_NOT_NULL);
                return responseData;
            }

            if (StringUtil.isBlank(timiReplyDto.getForumId())) {
                responseData.setFial();
                responseData.setMessage(Constant.FORUM_ID_NOT_NULL);
                return responseData;
            }

//            TimiUserDto timiUserDto = getLoginUser(request);
//            if (timiUserDto == null) {
//                responseData.setFial();
//                responseData.setMessage(Constant.FORUM_USER_NOT_LOGIN);
//                return responseData;
//            }
//            timiReplyDto.setUserId(timiUserDto.getUserId());
//            timiReplyDto.setUserName(timiUserDto.getNickname());
//            timiReplyDto.setUserImageUrl(timiUserDto.getPic());
            timiReplyDto.setUserId("133232");
            timiReplyDto.setUserName("133232");
            timiReplyDto.setUserImageUrl("133232");
            boolean b = this.timiReplyService.addForum(timiReplyDto);
            if (!b){
                responseData.setFial();
                responseData.setMessage(Constant.SYSTEM_ERROR);
                return responseData;
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    /**
     * 分页查询
     * @param timiReplyDto
     * @return
     */
    @PostMapping("/findReply")
    public String findReply(TimiReplyDto timiReplyDto) {
        log.info("Reply findReply  request :{}",timiReplyDto);
        ResponseData responseData = new ResponseData();
        if (StringUtil.isBlank(timiReplyDto.getForumId())) {
            responseData.setFial();
            responseData.setMessage(Constant.FORUM_ID_NOT_NULL);
            return JSONUtils.toJosnString(responseData);
        }
        try {

            PageInfo<TimiReplyDto> pageInfo = this.timiReplyService.findPage(timiReplyDto);
            responseData.setData(pageInfo);
        } catch (Exception e) {
            log.error("Reply findReply error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return JSONUtils.toJosnString(responseData);
    }


}
