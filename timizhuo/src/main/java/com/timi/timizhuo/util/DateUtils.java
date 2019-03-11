package com.timi.timizhuo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 日期工具类
 * @Auther timi
 * @Date 2018/12/9 000914:00
 */
public class DateUtils {

    public static String dateFormat(Date date, String formatStr) {
        DateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }
}
