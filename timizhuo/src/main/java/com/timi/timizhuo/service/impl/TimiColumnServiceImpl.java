package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.entity.TimiColumn;
import com.timi.timizhuo.mapper.TimiColumnMapper;
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
public class TimiColumnServiceImpl extends ServiceImpl<TimiColumnMapper, TimiColumn> implements TimiColumnService {

}
