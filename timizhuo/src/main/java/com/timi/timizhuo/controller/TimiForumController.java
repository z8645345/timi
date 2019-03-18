package com.timi.timizhuo.controller;

import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.dto.TimiForumDto;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.service.TimiForumService;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("forum")
@Slf4j
public class TimiForumController extends  BaseController{

    private TimiForumService timiForumService;

    /**
     * 发帖
     * @param timiForumDto
     * @param request
     * @return
     */
    @PostMapping("/addForum")
    public ResponseData findAll(@RequestBody TimiForumDto timiForumDto , HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        log.info("addForum  timiForumDto :{}",timiForumDto );
        try {
            if (timiForumDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtil.isBlank(timiForumDto.getForumTitle())) {
                responseData.setFial();
                responseData.setMessage(Constant.TITLE_NOT_NULL);
                return responseData;
            }
            if (StringUtil.isBlank(timiForumDto.getForumContent())) {
                responseData.setFial();
                responseData.setMessage(Constant.CONTENT_NOT_NULL);
                return responseData;
            }
            TimiUserDto timiUserDto = getLoginUser(request);
            timiForumDto.setUserId(timiUserDto.getUserId());
            boolean b = this.timiForumService.addForum(timiForumDto);
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

    @PostMapping("/findForum")
    public ResponseData findForum(@RequestBody TimiForumDto timiForumDto , HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        log.info("addForum  timiForumDto :{}",timiForumDto );
         //TODO
        return responseData;
    }


}
