package com.timi.timizhuo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timi.timizhuo.annotation.TimiLogin;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.entity.TimiForum;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.service.TimiForumService;
import com.timi.timizhuo.util.JSONUtils;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tio.utils.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * 论坛中心
 * @author  Cruisin
 */
@RestController
@RequestMapping("/forum")
@Slf4j
@CrossOrigin()
public class TimiForumController extends  BaseController{

    @Autowired
    private TimiForumService timiForumService;

    /**
     * 发帖
     * @param timiForumDto
     * @param request
     * @return
     */
    @TimiLogin
    @PostMapping("/addForum")
    public ResponseData addForum(TimiForum timiForumDto , HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        log.info("forum  addForum  timiForumDto :{}",timiForumDto );
        try {
            if (timiForumDto == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtil.isBlank(timiForumDto.getForumContent())) {
                responseData.setFial();
                responseData.setMessage(Constant.CONTENT_NOT_NULL);
                return responseData;
            }
            TimiUser timiUser = getLoginUser(request);
            if (timiUser == null) {
                responseData.setFial();
                responseData.setMessage(Constant.FORUM_USER_NOT_LOGIN);
                return responseData;
            }
            timiForumDto.setUserId(timiUser.getId());
            timiForumDto.setUserName(timiUser.getNickname());
            timiForumDto.setUserImageUrl(timiUser.getPic());
            boolean b = this.timiForumService.addForum(timiForumDto);
            if (!b){
                responseData.setFial();
                responseData.setMessage(Constant.SYSTEM_ERROR);
                return responseData;
            }
        } catch (Exception e) {
            log.error("forum addForum error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
    /**
     * 分页查询
     * @param timiForumDto
     * @return
     */
    @PostMapping("/findForum")
    public String findForum(TimiForum timiForumDto) {
        log.info("forum findForum  request :{}",timiForumDto);
        ResponseData responseData = new ResponseData();
        try {
            IPage<TimiForum> result = timiForumService.page(timiForumDto.descPage("posted_time"), new QueryWrapper<>(timiForumDto));
            responseData.setData(result);
        } catch (Exception e) {
            log.error("forum findForum error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return JSONUtils.toJosnString(responseData, "yyyy-MM-dd HH:mm");
    }

    /**
     * 置顶数据查询
     * @return
     */
//    @TimiLogin
    @PostMapping("/findForumByStick")
    public String findForumByStick() {
        ResponseData responseData = new ResponseData();
        try {
            List<TimiForum> list = this.timiForumService.findForumByStick();
            setPostedTimeLong(list);
            responseData.setData(list);
        } catch (Exception e) {
            log.error("forum findForum error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return JSONUtils.toJosnString(responseData, "yyyy-MM-dd HH:mm");
    }

    private void setPostedTimeLong(List<TimiForum> pageInfo) {
        pageInfo.forEach(timiForumDto -> {
            if (timiForumDto.getPostedTime() != null) {
                timiForumDto.setPostedTimeLong(timiForumDto.getPostedTime().getTime());
            }
        });
    }

    /**
     * 修改阅读数或点赞数  1 点赞  2 阅读
     * timiForum
     */
    @PostMapping("/updateLikeAndRead")
    public ResponseData updateLikeAndRead(HttpServletRequest request, TimiForum timiForum) {
        log.info("forum updateLikeAndRead  request :{}",timiForum);
        ResponseData responseData = new ResponseData();
        try {
            if (timiForum == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isBlank(timiForum.getId())) {
                responseData.setFial();
                responseData.setMessage(Constant.ID_NOT_NULL);
                return responseData;
            }

            if (timiForum.getType() == null) {
                responseData.setFial();
                responseData.setMessage(Constant.SYSTEM_ERROR);
                return responseData;
            }
            if (timiForum.getType() == 1 && getLoginUser(request) == null) {
                responseData.setFial();
                responseData.setMessage(Constant.UPDATE_USER_NOT_LOGIN);
                return responseData;
            }
            this.timiForumService.updateLikeAndRead(timiForum);
        } catch (Exception e) {
            log.error("forum updateLikeAndRead error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;

    }

    /**
     * 查询当前用户的帖子
     * request
     */
    @TimiLogin
    @PutMapping("/findUserForum")
    public ResponseData findUserForum(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            TimiUser timiUser = getLoginUser(request);
            if (timiUser == null) {
                responseData.setFial();
                responseData.setMessage(Constant.FORUM_USER_NOT_LOGIN);
                return responseData;
            }
            List<TimiForum>lists =this.timiForumService.findForumByUserId(timiUser.getId());
            responseData.setData(lists);
            responseData.setSuccess();
        } catch (Exception e) {
            log.error("forum findUserForum error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;

    }

    /**
     * 根据主贴id查询
     * request
     */
    @PostMapping("/findForumById")
    public ResponseData findForumById(TimiForum timiForum) {
        ResponseData responseData = new ResponseData();
        try {

            if (timiForum == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtil.isBlank(timiForum.getId())) {
                responseData.setFial();
                responseData.setMessage(Constant.ID_NOT_NULL);
                return responseData;
            }
            TimiForum timiForumData =  timiForumService.getById(timiForum.getId());
            responseData.setData(timiForumData);
            responseData.setSuccess();
        } catch (Exception e) {
            log.error("forum findForumById error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;

    }

    /**
     * 根据用户id查询
     * request
     */
    @PostMapping("/findForumByUserId")
    public String findForumByUserId(TimiForum timiForum) {
        ResponseData responseData = new ResponseData();
        try {

            if (timiForum == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return JSONUtils.toJosnString(responseData, "yyyy-MM-dd");
            }
            if (StringUtil.isBlank(timiForum.getUserId())) {
                responseData.setFial();
                responseData.setMessage(Constant.ID_NOT_NULL);
                return JSONUtils.toJosnString(responseData, "yyyy-MM-dd");
            }
            List<TimiForum> list = timiForumService.list(new QueryWrapper<TimiForum>(timiForum).orderByDesc("posted_time"));
            if (list == null) {
                list = Collections.emptyList();
            }
            responseData.setData(list);
            responseData.setSuccess();
        } catch (Exception e) {
            log.error("forum findForumByUserId error ", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return JSONUtils.toJosnString(responseData, "yyyy-MM-dd");

    }
}
