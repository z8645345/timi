package com.timi.timizhuo;

import com.alibaba.fastjson.JSONObject;
import com.timi.timizhuo.util.HttpAsyncClient;
import com.timi.timizhuo.util.HttpAsyncUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public static void main(String[] args) throws  Exception {
//        RestTemplate restTemplate = new RestTemplate();
        String url = "https://m.weibo.cn/api/container/getIndex?type=uid&value=1254461195&containerid=1076031254461195";
//        String o = restTemplate.getForObject(url, String.class);
//        System.out.println(o);
        while (true) {
            JSONObject jsonObject = HttpAsyncUtil.getRequestFromUrl(url);
            String id = jsonObject.getJSONObject("data").getJSONArray("cards").getJSONObject(0).getJSONObject("mblog").getString("id");
            String text = jsonObject.getJSONObject("data").getJSONArray("cards").getJSONObject(0).getJSONObject("mblog").getString("text");
            System.out.println(id);
            System.out.println(text);
            Thread.sleep(10000);
        }
    }
}
