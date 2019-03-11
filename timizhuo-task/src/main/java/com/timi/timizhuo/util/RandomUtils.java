package com.timi.timizhuo.util;

import java.util.Random;

/**
 * @Description 随机数工具类
 * @Author timi
 * @Date 2018/9/4 17:08
 **/
public class RandomUtils {
    public static Random random = new Random();

    public static int nextInt(int number) {
        return random.nextInt(number);
    }

    public static int nextInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
