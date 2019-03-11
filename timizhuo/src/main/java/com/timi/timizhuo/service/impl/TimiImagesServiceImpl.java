package com.timi.timizhuo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.timi.timizhuo.common.ColumnEnum;
import com.timi.timizhuo.dao.mapper.TimiColumnMapper;
import com.timi.timizhuo.dao.mapper.TimiImagesMapper;
import com.timi.timizhuo.dao.mapper.TimiSinaWeiboMapper;
import com.timi.timizhuo.dao.model.TimiColumn;
import com.timi.timizhuo.dao.model.TimiImages;
import com.timi.timizhuo.dao.model.TimiSinaWeibo;
import com.timi.timizhuo.dto.TimiColumnDto;
import com.timi.timizhuo.dto.TimiImagesDto;
import com.timi.timizhuo.dto.TimiShowDto;
import com.timi.timizhuo.response.DateData;
import com.timi.timizhuo.response.FindByColumnLimitResDTO;
import com.timi.timizhuo.service.TimiImagesService;
import com.timi.timizhuo.util.BeanConvertUtils;
import com.timi.timizhuo.util.DateUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 卓依婷图片service实现类
 * @Auther timi
 * @Date 2018/12/9 000913:45
 */
@Service
public class TimiImagesServiceImpl implements TimiImagesService {

    @Autowired
    private TimiSinaWeiboMapper timiSinaWeiboMapper;

    @Autowired
    private TimiColumnMapper timiColumnMapper;

    @Autowired
    private TimiImagesMapper timiImagesMapper;

    @Override
    public void syncWeiboImages() {
        List<TimiSinaWeibo> timiSinaWeibos = timiSinaWeiboMapper.findAll();
        for(TimiSinaWeibo timiSinaWeibo : timiSinaWeibos) {
            if (StringUtil.isNotEmpty(timiSinaWeibo.getOriginalPics())) {
                String columnNo = "WB" +  DateUtils.dateFormat(timiSinaWeibo.getCreatedAt(), "yyyyMMddHHmmss");
                TimiColumn timiColumn = timiColumnMapper.findByColumnNo(columnNo);
                if (timiColumn == null) {
                    timiColumn = new TimiColumn();
                    timiColumn.setColumnNo(columnNo);
                    timiColumn.setColumnName(DateUtils.dateFormat(timiSinaWeibo.getCreatedAt(), "yyyy年MM月dd日HH时mm分ss秒") + "微博图片");
                    timiColumn.setColumnType(ColumnEnum.IMAGE.getType());
                    timiColumn.setColumnTime(timiSinaWeibo.getCreatedAt());
                    timiColumnMapper.insert(timiColumn);

                    String[] pics = timiSinaWeibo.getOriginalPics().split(";");
                    for (String pic : pics) {
                        TimiImages timiImages = new TimiImages();
                        timiImages.setImgUrl(pic);
                        timiImages.setThumbnailUrl(pic);
                        timiImages.setAlt(timiColumn.getColumnName());
                        timiImages.setColumnNo(columnNo);
                        timiImages.setCreateTime(timiSinaWeibo.getCreatedAt());
                        timiImages.setUpdateTime(timiSinaWeibo.getCreatedAt());
                        timiImagesMapper.insert(timiImages);
                    }
                }
            }
        }
    }

    @Override
    public PageInfo<TimiImagesDto> findByColumn(TimiImagesDto timiImagesDto) {
        String columnNo = null;
        if (timiImagesDto != null) {
            columnNo = timiImagesDto.getColumnNo();
        }
        PageHelper.startPage(timiImagesDto.getPageNum(), timiImagesDto.getPageSize());
        List<TimiImages> timiImages = timiImagesMapper.findByColumnNo(columnNo);
        List<TimiImagesDto> list = BeanConvertUtils.convertList(timiImages, TimiImagesDto.class);
        PageInfo<TimiImagesDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<FindByColumnLimitResDTO> findByColumnLimit(TimiColumnDto timiColumnDto) {
        PageHelper.startPage(timiColumnDto.getPageNum(), timiColumnDto.getPageSize());
        TimiColumn timiColumn = new TimiColumn();
        timiColumn.setColumnType("("+ ColumnEnum.IMAGE.getType() +")");
        List<TimiColumn> timiColumnList = timiColumnMapper.findByCondition(timiColumn);
        List<FindByColumnLimitResDTO> findByColumnLimitResDTOS = Lists.newArrayList();
        timiColumnList.forEach(timiColumn1 -> {
            List<TimiImages> timiImages = timiImagesMapper.findByColumnNo(timiColumn1.getColumnNo());
            String year = DateUtils.dateFormat(timiColumn1.getColumnTime(), "yyyy年");
            String data = DateUtils.dateFormat(timiColumn1.getColumnTime(), "MM月dd日");
            List<TimiImagesDto> timiImagesDtoList = BeanConvertUtils.convertList(timiImages, TimiImagesDto.class);
            FindByColumnLimitResDTO dto = yearContainData(findByColumnLimitResDTOS, year);
            if (dto != null) {
                DateData dateData = new DateData();
                dateData.setData(data);
                dateData.setTitle(timiColumn1.getColumnName());
                dateData.setImagesDtoList(timiImagesDtoList);
                dto.getDateData().add(dateData);
            } else {
                FindByColumnLimitResDTO findByColumnLimitResDTO = new FindByColumnLimitResDTO();
                findByColumnLimitResDTO.setYear(year);
                DateData dateData = new DateData();
                dateData.setData(data);
                dateData.setTitle(timiColumn1.getColumnName());
                dateData.setImagesDtoList(timiImagesDtoList);
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
