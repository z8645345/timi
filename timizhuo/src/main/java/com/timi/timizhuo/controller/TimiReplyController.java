package com.timi.timizhuo.controller;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.entity.TimiReply;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.service.TimiReplyService;
import com.timi.timizhuo.util.JSONUtils;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseData addReply(@RequestBody TimiReply timiReplyDto , HttpServletRequest request) {
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

            TimiUser timiUse = getLoginUser(request);
            if (timiUse == null) {
                responseData.setFial();
                responseData.setMessage(Constant.FORUM_USER_NOT_LOGIN);
                return responseData;
            }
            timiReplyDto.setUserId(timiUse.getId());
            timiReplyDto.setUserName(timiUse.getNickname());
            timiReplyDto.setUserImageUrl(timiUse.getPic());
//            timiReplyDto.setUserId("32323");
//            timiReplyDto.setUserName("timie");
//            timiReplyDto.setUserImageUrl("43434");
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
    public String findReply(TimiReply timiReplyDto) {
        log.info("Reply findReply  request :{}",timiReplyDto);
        ResponseData responseData = new ResponseData();
        if (StringUtil.isBlank(timiReplyDto.getForumId())) {
            responseData.setFial();
            responseData.setMessage(Constant.FORUM_ID_NOT_NULL);
            return JSONUtils.toJosnString(responseData);
        }
        try {

            PageInfo<TimiReply> pageInfo = this.timiReplyService.findPage(timiReplyDto);
            responseData.setData(pageInfo);
        } catch (Exception e) {
            log.error("Reply findReply error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return JSONUtils.toJosnString(responseData);
    }


}
