package com.timi.timizhuo.util;

import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @Description base64工具类
 * @Auther timi
 * @Date 2019/3/19 0019
 */
public class Base64Utils {

    /**
     * base64转byte数组
     * @param str
     * @return
     */
    public static byte[] base64ToBytes(String str) {
        BASE64Decoder decode = new BASE64Decoder();
        byte[] b = null;
        try {
            b = decode.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}
