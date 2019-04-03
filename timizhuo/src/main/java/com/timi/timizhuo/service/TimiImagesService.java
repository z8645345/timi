package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timi.timizhuo.entity.TimiColumn;
import com.timi.timizhuo.entity.TimiImages;
import com.timi.timizhuo.response.FindByColumnLimitResDTO;

import java.util.List;

/**
 * @Description 卓依婷图片service
 * @Auther timi
 * @Date 2018/12/9 0009 13:45
 */
public interface TimiImagesService extends IService<TimiImages> {
    void syncWeiboImages();

    List<FindByColumnLimitResDTO> findByColumnLimit(TimiColumn timiColumn);
}
