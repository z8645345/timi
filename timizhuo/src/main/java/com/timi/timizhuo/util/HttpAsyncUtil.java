package com.timi.timizhuo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.util.concurrent.Future;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @Description 异步的HTTP请求工具类
 * @Auther timi
 * @Date 2018/11/5 000514:20
 */
public class HttpAsyncUtil {

    private static final Logger logger = getLogger(HttpAsyncUtil.class);
    private static HttpAsyncClient httpAsyncClient = new HttpAsyncClient();

    public static JSONObject getRequestFromUrl(String url) throws Exception {
//        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpAsyncClient.getAsyncHttpClient().start();
            HttpGet request = new HttpGet(url);
            request.setHeader("Connection","close");
            Future<HttpResponse> future = httpAsyncClient.getAsyncHttpClient().execute(request, null);
            HttpResponse response = future.get();
            String entityStr = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.info("m:getRequestFromUrl 请求失败，状态:{}, response:{}", response.getStatusLine(), entityStr);
            }
            return JSONObject.parseObject(entityStr);
        } finally {
//            httpclient.close();
        }
    }

    public static String getStrRequestFromUrl(String url) throws Exception {
        try {
            httpAsyncClient.getAsyncHttpClient().start();
            HttpGet request = new HttpGet(url);
            request.setHeader("Connection","close");
            Future<HttpResponse> future = httpAsyncClient.getAsyncHttpClient().execute(request, null);
            HttpResponse response = future.get();
            String entityStr = EntityUtils.toString(response.getEntity());
            return entityStr;
        } finally {
//            httpclient.close();
        }
    }
}
