package com.timi.timizhuo.service.impl;

import com.timi.timizhuo.dao.mapper.TimiColumnMapper;
import com.timi.timizhuo.dao.model.TimiColumn;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.service.TimiColumnService;
import com.timi.timizhuo.util.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 卓依婷视频栏目service实现类
 * @Auther timi
 * @Date 2018/12/2 000213:51
 */
@Service
public class TimiColumnServiceImpl implements TimiColumnService {

    @Autowired
    private TimiColumnMapper timiColumnMapper;

    @Override
    public List<TimiColumnDto> findByColumnType(TimiColumnDto timiColumnDto) {
        TimiColumn timiColumn = new TimiColumn();
        BeanConvertUtils.convert(timiColumnDto, timiColumn);
        List<TimiColumn> timiColumns = timiColumnMapper.findByCondition(timiColumn);
        for(TimiColumn t : timiColumns) {
            t.setColumnTimeLong(t.getColumnTime().getTime());
        }
        return BeanConvertUtils.convertList(timiColumns, TimiColumnDto.class);
    }
}
