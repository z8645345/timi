package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.StringUtil;
import com.timi.timizhuo.dto.response.DateData;
import com.timi.timizhuo.dto.response.FindByColumnLimitResDTO;
import com.timi.timizhuo.enums.ColumnEnum;
import com.timi.timizhuo.entity.TimiColumn;
import com.timi.timizhuo.entity.TimiImages;
import com.timi.timizhuo.entity.TimiSinaWeibo;
import com.timi.timizhuo.mapper.TimiColumnMapper;
import com.timi.timizhuo.mapper.TimiImagesMapper;
import com.timi.timizhuo.mapper.TimiSinaWeiboMapper;
import com.timi.timizhuo.service.TimiImagesService;
import com.timi.timizhuo.util.DateUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 卓依婷图片service实现类
 * @Auther timi
 * @Date 2018/12/9 000913:45
 */
@Service
public class TimiImagesServiceImpl extends ServiceImpl<TimiImagesMapper, TimiImages> implements TimiImagesService {

    @Autowired
    private TimiSinaWeiboMapper timiSinaWeiboMapper;

    @Autowired
    private TimiColumnMapper timiColumnMapper;

    @Autowired
    private TimiImagesMapper timiImagesMapper;

    @Override
    public void syncWeiboImages() {
        List<TimiSinaWeibo> timiSinaWeibos = timiSinaWeiboMapper.selectList(null);
        for(TimiSinaWeibo timiSinaWeibo : timiSinaWeibos) {
            if (StringUtil.isNotEmpty(timiSinaWeibo.getOriginalPics())) {
                String columnNo = "WB" +  DateUtils.dateFormat(timiSinaWeibo.getCreatedAt(), "yyyyMMddHHmmss");
                TimiColumn timiColumn = timiColumnMapper.selectOne(new QueryWrapper<TimiColumn>().eq("column_no", columnNo));
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
    public List<FindByColumnLimitResDTO> findByColumnLimit(TimiColumn timiColumn) {
        Page<TimiColumn> page = new Page<>();
        page.setCurrent(timiColumn.getPageNum());
        page.setSize(timiColumn.getPageSize());
        page.setDesc("create_time");
        List<String> list = Lists.newArrayList();
        list.add(ColumnEnum.IMAGE.getType());
        IPage<TimiColumn> pageList = timiColumnMapper.selectPage(page, new QueryWrapper<TimiColumn>().in("column_type", list));
        List<TimiColumn> timiColumnList = pageList.getRecords();

        List<FindByColumnLimitResDTO> findByColumnLimitResDTOS = Lists.newArrayList();
        timiColumnList.forEach(timiColumn1 -> {
            List<TimiImages> timiImages = timiImagesMapper.selectList(new QueryWrapper<TimiImages>().eq("column_no", timiColumn1.getColumnNo()));
            String year = DateUtils.dateFormat(timiColumn1.getColumnTime(), "yyyy年");
            String data = DateUtils.dateFormat(timiColumn1.getColumnTime(), "MM月dd日");
            FindByColumnLimitResDTO dto = yearContainData(findByColumnLimitResDTOS, year);
            if (dto != null) {
                DateData dateData = new DateData();
                dateData.setData(data);
                dateData.setTitle(timiColumn1.getColumnName());
                dateData.setImagesDtoList(timiImages);
                dto.getDateData().add(dateData);
            } else {
                FindByColumnLimitResDTO findByColumnLimitResDTO = new FindByColumnLimitResDTO();
                findByColumnLimitResDTO.setYear(year);
                DateData dateData = new DateData();
                dateData.setData(data);
                dateData.setTitle(timiColumn1.getColumnName());
                dateData.setImagesDtoList(timiImages);
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
