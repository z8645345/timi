package com.timi.timizhuo.util;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 短信工具类
 * @Auther timi
 * @Date 2019/4/26 0026
 */
public class SmsUtils {

    public static String send(String mobile, String checkCode) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("sid", "b99f5a0691aca1f17550f990160118d6");
        map.put("token", "e1e3d3a91a1a46077dec2e6a23b2f2ed");
        map.put("appid", "3a2a272c5c174c09a2c4e941f142f5b4");
        map.put("templateid", "417451");
        map.put("param", checkCode + ",5");
        map.put("mobile", mobile);
        String result = restTemplate.postForObject("https://open.ucpaas.com/ol/sms/sendsms", map, String.class);
        return result;
    }
}
