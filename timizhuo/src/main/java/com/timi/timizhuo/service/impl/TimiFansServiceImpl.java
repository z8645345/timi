package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.entity.TimiFans;
import com.timi.timizhuo.mapper.TimiFansMapper;
import com.timi.timizhuo.service.ITimiFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author timi
 * @since 2019-04-07
 */
@Service
public class TimiFansServiceImpl extends ServiceImpl<TimiFansMapper, TimiFans> implements ITimiFansService {
    @Autowired
    private TimiFansMapper timiFansMapper;

    @Override
    public ServiceResponseData<List<TimiFans>> selectByMap(TimiFans timiFans) throws Exception {
        ServiceResponseData<List<TimiFans>> serviceResponseData = new ServiceResponseData<>();
        Map map = new HashMap<>();
        if(timiFans.getUserId()>0){
            map.put("user_id", timiFans.getUserId());
        }else{
            map.put("parent_id", timiFans.getParentId());
        }
        List<TimiFans> list = timiFansMapper.selectByMap(map);
        serviceResponseData.setData(list);
        serviceResponseData.setSuccess();
        return serviceResponseData;
    }
}
