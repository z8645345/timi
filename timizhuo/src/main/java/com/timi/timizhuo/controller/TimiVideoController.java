package com.timi.timizhuo.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timi.timizhuo.common.Constant;
import com.timi.timizhuo.common.ResponseData;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.enums.UserRocordEnum;
import com.timi.timizhuo.dto.response.FindByColumnLimitResDTO;
import com.timi.timizhuo.entity.TimiColumn;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.entity.TimiUserRecord;
import com.timi.timizhuo.entity.TimiVideo;
import com.timi.timizhuo.service.TimiUserRecordService;
import com.timi.timizhuo.service.TimiVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 卓依婷视频controller
 */
@RestController
@RequestMapping(value = "/video")
public class TimiVideoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TimiVideoController.class);

    @Autowired
    private TimiVideoService timiVideoService;

    @Autowired
    private TimiUserRecordService timiUserRecordService;

    @PostMapping("/findByColumn")
    public ResponseData findByColumn(TimiVideo timiVideo, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiVideo == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else {
                Page<TimiVideo> page = new Page<>();
                page.setCurrent(timiVideo.getPageNum());
                page.setSize(timiVideo.getPageSize());
                page.setDesc("create_time");
                QueryWrapper<TimiVideo> wrapper = null;
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(timiVideo.getColumnNo())) {
                    wrapper = new QueryWrapper<TimiVideo>().eq("column_no", timiVideo.getColumnNo());
                }
                IPage<TimiVideo> result = timiVideoService.page(page, wrapper);
                setUserRecord(result.getRecords(), request);
                responseData.setData(result.getRecords());
            }
        } catch (Exception e) {
            logger.error("m:register 根据栏目查询视频失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/randomVideo")
    public ResponseData randomVideo(TimiVideo timiVideo, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiVideo == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else {
                List<TimiVideo> timiColumnList = timiVideoService.randomVideo(timiVideo);
                setUserRecord(timiColumnList, request);
                responseData.setData(timiColumnList);
            }
        } catch (Exception e) {
            logger.error("m:register 根据栏目查询视频失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/findById")
    public ResponseData findById(TimiVideo timiVideo, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiVideo == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
            } else {
                TimiVideo resultTimiVideo = timiVideoService.getById(timiVideo.getId());
                setUserRecord(resultTimiVideo, request);
                responseData.setData(resultTimiVideo);
            }
        } catch (Exception e) {
            logger.error("m:register 根据id查询视频失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    @PostMapping("/showVideo")
    public ResponseData showVideo(TimiVideo timiVideo) {
        ResponseData responseData = new ResponseData();
        try {
            if (timiVideo == null) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            if (StringUtils.isEmpty(timiVideo.getId())) {
                responseData.setFial();
                responseData.setMessage(Constant.PARAMS_NOT_NULL);
                return responseData;
            }
            ServiceResponseData<Long> serviceResponseData = timiVideoService.showVideo(timiVideo);
            if (serviceResponseData.isSuccess()) {
                responseData.setData(serviceResponseData.getData());
            } else {
                responseData.setFial();
                responseData.setMessage(serviceResponseData.getMessage());
            }
        } catch (Exception e) {
            logger.error("m:showVideo 播放视频次数加1失败", e);
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }

    private void setUserRecord(List<TimiVideo> timiColumnList, HttpServletRequest request) {
        TimiUser timiUser = getLoginUser(request);
        if (timiUser != null) {
            List<TimiUserRecord> timiUserRecordList = timiUserRecordService.list(new QueryWrapper<TimiUserRecord>().eq("username", timiUser.getUsername()));
            timiColumnList.forEach(timiVideo -> timiUserRecordList.forEach(timiUserRecord -> checkUserRecord(timiUserRecord, timiVideo)));
        }
    }

    private void setUserRecord(TimiVideo timiVideoDto, HttpServletRequest request) {
        TimiUser timiUser = getLoginUser(request);
        if (timiUser != null) {
            List<TimiUserRecord> timiUserRecordList = timiUserRecordService.list(new QueryWrapper<TimiUserRecord>().eq("username", timiUser.getUsername()));
            timiUserRecordList.forEach(timiUserRecord -> checkUserRecord(timiUserRecord, timiVideoDto));
        }
    }

    private void checkUserRecord(TimiUserRecord timiUserRecord, TimiVideo timiVideo) {
        if (timiUserRecord.getRecordId().equals(timiVideo.getId())) {
            if (timiUserRecord.getType().equals(UserRocordEnum.VIDOE_LOVE.getType())) {
                timiVideo.setUserLove(true);
            } else if (timiUserRecord.getType().equals(UserRocordEnum.VIDEO_COLLECTION.getType())) {
                timiVideo.setUserCollection(true);
            }
        }
    }

    @PostMapping("/findByColumnLimit")
    public ResponseData findByColumnLimit(TimiColumn timiColumn) {
        ResponseData responseData = new ResponseData();
        try {
            List<FindByColumnLimitResDTO> findByColumnLimitResDTOS = timiVideoService.findByColumnLimit(timiColumn);
            responseData.setData(findByColumnLimitResDTOS);
        } catch (Exception e) {
            responseData.setFial();
            responseData.setMessage(Constant.SYSTEM_ERROR);
        }
        return responseData;
    }
}
