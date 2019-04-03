package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.common.UserRocordEnum;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.dto.TimiUserRecordDto;
import com.timi.timizhuo.entity.TimiUserRecord;
import com.timi.timizhuo.entity.TimiVideo;
import com.timi.timizhuo.mapper.TimiUserRecordMapper;
import com.timi.timizhuo.mapper.TimiVideoMapper;
import com.timi.timizhuo.service.TimiUserRecordService;
import com.timi.timizhuo.util.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/17 001712:57
 */
@Service
public class TimiUserRecordServiceImpl extends ServiceImpl<TimiUserRecordMapper, TimiUserRecord> implements TimiUserRecordService {

    @Autowired
    private TimiUserRecordMapper timiUserRecordMapper;

    @Autowired
    private TimiVideoMapper videoMapper;

    @Override
    public ServiceResponseData<String> saveUserRecord(TimiUserRecordDto timiUserRecordDto) {
        TimiUserRecord timiUserRecord = new TimiUserRecord();
        BeanConvertUtils.convert(timiUserRecordDto, timiUserRecord);
        if (UserRocordEnum.VIDOE_LOVE.getType().equals(timiUserRecordDto.getType())) {
            TimiVideo timiVideo = videoMapper.selectById(timiUserRecordDto.getRecordId());
            if (timiVideo != null) {
                timiVideo.setLoveCount(timiVideo.getLoveCount() + 1);
                videoMapper.updateById(timiVideo);
            }
        } else if (UserRocordEnum.VIDEO_COLLECTION.getType().equals(timiUserRecordDto.getType())) {
            TimiVideo timiVideo = videoMapper.selectById(timiUserRecordDto.getRecordId());
            if (timiVideo != null) {
                timiVideo.setCollectionCount(timiVideo.getCollectionCount() + 1);
                videoMapper.updateById(timiVideo);
            }
        }
        timiUserRecordMapper.insert(timiUserRecord);
        ServiceResponseData<String> serviceResponseData = new ServiceResponseData<>();
        serviceResponseData.setSuccess();
        return serviceResponseData;
    }
}
