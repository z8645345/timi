package com.timi.timizhuo.feige;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.timi.timizhuo.dto.NoticeDto;
import com.timi.timizhuo.dto.NoticeValueDto;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 发送飞鸽快信
 * @author 曾佳
 */
public class SendWechatMsgUtils {

    private static Logger logger = LoggerFactory.getLogger(SendWechatMsgUtils.class);

    public static void sendMsg(String title,String content,String remark, String url) throws Exception {
        String appKey = "1a75db7b11a1d0014785f457f225606a";
        sendMsg(title, content, remark, url, appKey);
    }

    public static void sendMsg(String title,String content,String remark, String url, String appKey) throws Exception {
        // 创建一个httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建一个post对象
        HttpPost post = new HttpPost("https://u.ifeige.cn/api/message/send");
        // 创建一个Entity，模拟表单数据
        List<NameValuePair> formList = new ArrayList<NameValuePair>();

        NoticeDto noticeDto = new NoticeDto();
        NoticeValueDto first = new NoticeValueDto();
        first.setValue(title);
        first.setColor("#173177");
        noticeDto.setFirst(first);
        NoticeValueDto keyword1 = new NoticeValueDto();
        keyword1.setValue(content);
        keyword1.setColor("#173177");
        noticeDto.setKeyword1(keyword1);
        NoticeValueDto keyword2 = new NoticeValueDto();
        keyword2.setValue("卓依婷官方粉丝团");
        keyword2.setColor("#173177");
        noticeDto.setKeyword2(keyword2);
        NoticeValueDto keyword3 = new NoticeValueDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        keyword3.setValue(formatter.format(LocalDateTime.now()));
        keyword3.setColor("#173177");
        noticeDto.setKeyword3(keyword3);
        NoticeValueDto remarkO = new NoticeValueDto();
        remarkO.setValue(remark);
        remarkO.setColor("#173177");
        noticeDto.setRemark(remarkO);

        // 添加表单数据
        formList.add(new BasicNameValuePair("secret", "6e3510beabe2279d8d17e720837fd10a"));
        formList.add(new BasicNameValuePair("app_key", appKey));
        formList.add(new BasicNameValuePair("template_id", "5uZIvSm5GAdUR1X25HNpjuOp6jSiL88v4opn4a4GLa0"));
        formList.add(new BasicNameValuePair("url", url));
        formList.add(new BasicNameValuePair("data", JSONObject.toJSONString(noticeDto)));
//        formList.add(new BasicNameValuePair("time", "time()"));

        // 包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
        // 设置请求的内容
        post.setEntity(entity);
        // 设置请求的报文头部的编码
        // post.setHeader(new BasicHeader("Content-Type",
        // "application/x-www-form-urlencoded; charset=utf-8"));
        // 设置期望服务端返回的编码
        // post.setHeader(new BasicHeader("Accept",
        // "text/plain;charset=utf-8"));
        // 执行post请求
        CloseableHttpResponse response = client.execute(post);
        // 获取响应码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            // 获取数据
            String resStr = EntityUtils.toString(response.getEntity());
            logger.info("m: sendMsg 推送成功, " + resStr);
        } else {
            logger.info("m: sendMsg 推送失败,推送状态" + statusCode);
        }
    }

    public static void main(String[] args) {
        try {
            SendWechatMsgUtils.sendMsg("测试通知", "这是一条异常测试通知", "这是一条异常测试通知", "https://www.baidu.com", "6072ff4e6bf55c9a72966d428c95fbe6");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
