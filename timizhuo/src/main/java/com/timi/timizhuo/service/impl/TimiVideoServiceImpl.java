package com.timi.timizhuo.service.impl;

import com.github.pagehelper.PageHelper;
import com.timi.timizhuo.common.ColumnEnum;
import com.timi.timizhuo.common.ServiceResponseData;
import com.timi.timizhuo.dao.mapper.TimiColumnMapper;
import com.timi.timizhuo.dao.mapper.TimiVideoMapper;
import com.timi.timizhuo.dao.model.TimiColumn;
import com.timi.timizhuo.dao.model.TimiImages;
import com.timi.timizhuo.dao.model.TimiVideo;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.dto.TimiImagesDto;
import com.timi.timizhuo.dto.TimiVideoDto;
import com.timi.timizhuo.response.DateData;
import com.timi.timizhuo.response.FindByColumnLimitResDTO;
import com.timi.timizhuo.service.TimiVideoService;
import com.timi.timizhuo.util.BeanConvertUtils;
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
public class TimiVideoServiceImpl implements TimiVideoService {

    @Autowired
    private TimiVideoMapper timiVideoMapper;

    @Autowired
    private TimiColumnMapper timiColumnMapper;

    @Override
    public List<TimiVideoDto> findByColumn(TimiVideoDto timiVideoDto) {
        TimiVideo timiVideo = new TimiVideo();
        BeanConvertUtils.convert(timiVideoDto, timiVideo);
        PageHelper.startPage(timiVideoDto.getPageNum(), timiVideoDto.getPageSize());
        List<TimiVideo> timiVideos = timiVideoMapper.findByCondition(timiVideo);
        return BeanConvertUtils.convertList(timiVideos, TimiVideoDto.class);
    }

    @Override
    public List<TimiVideoDto> randomVideo(TimiVideoDto timiVideoDto) {
        TimiVideo timiVideo = new TimiVideo();
        BeanConvertUtils.convert(timiVideoDto, timiVideo);
        List<TimiVideo> timiVideos = timiVideoMapper.findByCondition(timiVideo);
        Set<Integer> randomSet = new HashSet<>(timiVideoDto.getPageSize());
        Random random = new Random();
        while (randomSet.size() != timiVideoDto.getPageSize()) {
            randomSet.add(random.nextInt(timiVideos.size()));
        }
        List<TimiVideo> resultList = new ArrayList<>();
        for(Integer num : randomSet) {
            resultList.add(timiVideos.get(num));
        }
        return BeanConvertUtils.convertList(resultList, TimiVideoDto.class);
    }

    @Override
    public TimiVideoDto findById(TimiVideoDto timiVideoDto) {
        TimiVideo timiVideo = timiVideoMapper.selectByPrimaryKey(timiVideoDto.getId());
        BeanConvertUtils.convert(timiVideo, timiVideoDto);
        return timiVideoDto;
    }

    @Override
    public ServiceResponseData<Long> showVideo(TimiVideoDto timiVideoDto) {
        TimiVideo timiVideo = timiVideoMapper.selectByPrimaryKey(timiVideoDto.getId());
        timiVideo.setShowCount(timiVideo.getShowCount() + 1);
        int count = timiVideoMapper.updateByPrimaryKey(timiVideo);
        for (int i = 0; i < 5 && count <= 0; i ++) {
            count = timiVideoMapper.updateByPrimaryKey(timiVideo);
        }
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
    public List<FindByColumnLimitResDTO> findByColumnLimit(TimiColumnDto timiColumnDto) {
        PageHelper.startPage(timiColumnDto.getPageNum(), timiColumnDto.getPageSize());
        TimiColumn timiColumn = new TimiColumn();
        timiColumn.setColumnType(timiColumnDto.getColumnType());
        List<TimiColumn> timiColumnList = timiColumnMapper.findByCondition(timiColumn);
        List<FindByColumnLimitResDTO> findByColumnLimitResDTOS = Lists.newArrayList();
        timiColumnList.forEach(timiColumn1 -> {
            TimiVideo findTimiVideo = new TimiVideo();
            findTimiVideo.setColumnNo(timiColumn1.getColumnNo());
            List<TimiVideo> timiVideos = timiVideoMapper.findByCondition(findTimiVideo);
            String year = DateUtils.dateFormat(timiColumn1.getColumnTime(), "yyyy年");
            String data = DateUtils.dateFormat(timiColumn1.getColumnTime(), "MM月dd日");
            List<TimiVideoDto> timiVideoDtoList = BeanConvertUtils.convertList(timiVideos, TimiVideoDto.class);
            FindByColumnLimitResDTO dto = yearContainData(findByColumnLimitResDTOS, year);
            if (dto != null) {
                DateData dateData = new DateData();
                dateData.setData(data);
                dateData.setTitle(timiColumn1.getColumnName());
                dateData.setVideoDtoList(timiVideoDtoList);
                dto.getDateData().add(dateData);
            } else {
                FindByColumnLimitResDTO findByColumnLimitResDTO = new FindByColumnLimitResDTO();
                findByColumnLimitResDTO.setYear(year);
                DateData dateData = new DateData();
                dateData.setData(data);
                dateData.setTitle(timiColumn1.getColumnName());
                dateData.setVideoDtoList(timiVideoDtoList);
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
