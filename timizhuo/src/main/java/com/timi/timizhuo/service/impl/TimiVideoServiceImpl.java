package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dto.response.DateData;
import com.timi.timizhuo.dto.response.FindByColumnLimitResDTO;
import com.timi.timizhuo.entity.TimiColumn;
import com.timi.timizhuo.entity.TimiVideo;
import com.timi.timizhuo.mapper.TimiColumnMapper;
import com.timi.timizhuo.mapper.TimiVideoMapper;
import com.timi.timizhuo.service.TimiVideoService;
import com.timi.timizhuo.util.DateUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description 卓依婷视频service实现类
 * @Auther timi
 * @Date 2018/12/2 000215:26
 */
@Service
public class TimiVideoServiceImpl extends ServiceImpl<TimiVideoMapper, TimiVideo> implements TimiVideoService {

    @Autowired
    private TimiVideoMapper timiVideoMapper;

    @Autowired
    private TimiColumnMapper timiColumnMapper;

    @Override
    public List<TimiVideo> randomVideo(TimiVideo timiVideo) {
        List<TimiVideo> timiVideos = timiVideoMapper.selectPage(new Page<TimiVideo>().setCurrent(timiVideo.getPageNum()).setSize(timiVideo.getPageSize()), null).getRecords();
        Set<Integer> randomSet = new HashSet<>(timiVideo.getPageSize());
        Random random = new Random();
        while (randomSet.size() != timiVideo.getPageSize()) {
            randomSet.add(random.nextInt(timiVideos.size()));
        }
        List<TimiVideo> resultList = new ArrayList<>();
        for(Integer num : randomSet) {
            resultList.add(timiVideos.get(num));
        }
        return resultList;
    }

    @Override
    public ServiceResponseData<Long> showVideo(TimiVideo timiVideo) {
        TimiVideo timiVideo1 = timiVideoMapper.selectById(timiVideo.getId());
        timiVideo1.setShowCount(timiVideo.getShowCount() + 1);
        int count = timiVideoMapper.updateById(timiVideo1);
        ServiceResponseData<Long> serviceResponseData = new ServiceResponseData<>();
        if (count > 0) {
            serviceResponseData.setSuccess();
            serviceResponseData.setData(timiVideo.getShowCount());
        } else {
            serviceResponseData.setFail();
            serviceResponseData.setMessage("视频更新播放次数失败");
        }
        return serviceResponseData;
    }

    @Override
    public List<FindByColumnLimitResDTO> findByColumnLimit(TimiColumn timiColumn) {
        List<TimiColumn> timiColumnList = timiColumnMapper.selectPage(
                new Page<TimiColumn>().setCurrent(timiColumn.getPageNum())
                        .setSize(timiColumn.getPageSize()).setDesc("column_time"),
                new QueryWrapper<TimiColumn>().inSql("column_type", timiColumn.getColumnType())).getRecords();
        List<FindByColumnLimitResDTO> findByColumnLimitResDTOS = Lists.newArrayList();
        timiColumnList.forEach(timiColumn1 -> {
            TimiVideo findTimiVideo = new TimiVideo();
            findTimiVideo.setColumnNo(timiColumn1.getColumnNo());
            List<TimiVideo> timiVideos = timiVideoMapper.selectList(new QueryWrapper<TimiVideo>().eq("column_no", timiColumn1.getColumnNo()));
            String year = DateUtils.dateFormat(timiColumn1.getColumnTime(), "yyyy年");
            String data = DateUtils.dateFormat(timiColumn1.getColumnTime(), "MM月dd日");
            FindByColumnLimitResDTO dto = yearContainData(findByColumnLimitResDTOS, year);
            if (dto != null) {
                DateData dateData = new DateData();
                dateData.setData(data);
                dateData.setTitle(timiColumn1.getColumnName());
                dateData.setVideoDtoList(timiVideos);
                dto.getDateData().add(dateData);
            } else {
                FindByColumnLimitResDTO findByColumnLimitResDTO = new FindByColumnLimitResDTO();
                findByColumnLimitResDTO.setYear(year);
                DateData dateData = new DateData();
                dateData.setData(data);
                dateData.setTitle(timiColumn1.getColumnName());
                dateData.setVideoDtoList(timiVideos);
                List<DateData> dateDataList = new ArrayList<>();
                dateDataList.add(dateData);
                findByColumnLimitResDTO.setDateData(dateDataList);
                findByColumnLimitResDTOS.add(findByColumnLimitResDTO);
            }
        });
        return findByColumnLimitResDTOS;
    }

    private FindByColumnLimitResDTO yearContainData(List<FindByColumnLimitResDTO> findByColumnLimitResDTOS, String year) {
        for (FindByColumnLimitResDTO dto : findByColumnLimitResDTOS) {
            if (dto.getYear().equals(year)) {
                return dto;
            }
        }
        return null;
    }
}
