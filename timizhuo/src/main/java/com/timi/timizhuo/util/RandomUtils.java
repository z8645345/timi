package com.timi.timizhuo.util;

import java.util.Random;

/**
 * @Description 随机数工具类
 * @Auther timi
 * @Date 2018/12/6 000613:21
 */
public class RandomUtils {

    public static String digit6() {
        String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++) {
            flag.append(sources.charAt(rand.nextInt(10)) + "");
        }
        return flag.toString();
    }
}
