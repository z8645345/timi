package com.timi.timizhuo.controller;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.entity.TimiVideoComment;
import com.timi.timizhuo.service.TimiVideoCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/19 001907:30
 */

@RestController
@RequestMapping(value = "/video/comment")
public class TimiVideoCommentController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TimiVideoCommentController.class);

    @Autowired
    private TimiVideoCommentService timiVideoCommentService;

    @PostMapping("/findByVideo")
    public ResponseData findByVideo(TimiVideoComment timiVideoCommentDto) {
        ResponseData responseData = new ResponseData();
        try {
            PageInfo<TimiVideoComment> pageInfo = null;
            responseData.setData(pageInfo);
        } catch (Exception e) {
            logger.error("m:findByVideo 根据视频查询评论失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
