package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dto.TimiVideoDto;
import com.timi.timizhuo.entity.TimiColumn;
import com.timi.timizhuo.entity.TimiVideo;
import com.timi.timizhuo.response.FindByColumnLimitResDTO;

import java.util.List;

/**
 * @Description 卓依婷视频service
 * @Auther timi
 * @Date 2018/12/2 0002 15:26
 */
public interface TimiVideoService extends IService<TimiVideo> {

    List<TimiVideo> randomVideo(TimiVideo timiVideo);

    ServiceResponseData<Long> showVideo(TimiVideo timiVideo);

    List<FindByColumnLimitResDTO> findByColumnLimit(TimiColumn timiColumn);
}
