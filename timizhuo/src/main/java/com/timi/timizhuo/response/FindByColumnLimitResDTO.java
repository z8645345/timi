package com.timi.timizhuo.response;

import com.timi.timizhuo.dto.TimiImagesDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/29 0029
 */
@Data
public class FindByColumnLimitResDTO implements Serializable {

    private String year;

    private List<DateData> dateData;
}
