package com.timi.timizhuo.response;

import com.timi.timizhuo.entity.TimiImages;
import com.timi.timizhuo.entity.TimiVideo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/29 0029
 */
@Data
public class DateData implements Serializable {

    private String data;

    private String title;

    private List<TimiImages> imagesDtoList;

    private List<TimiVideo> videoDtoList;
}
