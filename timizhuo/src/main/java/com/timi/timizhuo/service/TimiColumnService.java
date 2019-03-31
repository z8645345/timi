package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.entity.TimiColumn;

import java.util.List;

/**
 * @Description 卓依婷视频栏目service
 * @Auther timi
 * @Date 2018/12/2 0002 13:50
 */
public interface TimiColumnService extends IService<TimiColumn> {

    /**
     * 根据栏目类型查询视频
     * @param timiColumnDto
     * @return
     */
    List<TimiColumnDto> findByColumnType(TimiColumnDto timiColumnDto);
}
