package com.timi.timizhuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.entity.TimiFans;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author timi
 * @since 2019-04-07
 */
public interface ITimiFansService extends IService<TimiFans> {
    ServiceResponseData<List<TimiFans>> selectByMap(TimiFans timiFans) throws Exception;
}
