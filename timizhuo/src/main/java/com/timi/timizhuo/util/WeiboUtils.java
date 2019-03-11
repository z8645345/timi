package com.timi.timizhuo.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class WeiboUtils {
	
	/**
	 * 获取新浪微博信息url
	 */
	private static final String GET_SINA_WEIBO_URL = "http://api01.bitspaceman.com:8000/post/weibo?apikey=EFoPstgagLRjbS786RbROmuFVau9XZDcoJ1uN2H3UxOADz7EzzjWVMypWKbTsP5x&uid=1254461195";
	
	/**
	 * 获取新浪微博用户信息url
	 */
	private static final String GET_WEIBO_USER_INFO_URL = "http://api01.bitspaceman.com:8000/profile/weibo?apikey=EFoPstgagLRjbS786RbROmuFVau9XZDcoJ1uN2H3UxOADz7EzzjWVMypWKbTsP5x&id=1254461195&type=1";
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject postRequestFromUrl(String url, String body) throws IOException, JSONException {
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		PrintWriter out = new PrintWriter(conn.getOutputStream());
		out.print(body);
		out.flush();

		InputStream instream = conn.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = JSONObject.parseObject(jsonText);
			return json;
		} finally {
			instream.close();
		}
	}

	public static JSONObject getRequestFromUrl(String url) throws IOException, JSONException {
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		InputStream instream = conn.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = JSONObject.parseObject(jsonText);
			return json;
		} finally {
			instream.close();
		}
	}
	
	/**
	 * 获取卓依婷新浪微博信息
	 * @return
	 */
	public static JSONObject getTimiSinaWeibo() throws IOException, JSONException {
		return getRequestFromUrl(GET_SINA_WEIBO_URL);
	}
	
	/**
	 * 获取卓依婷新浪微博用户信息
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject getTimiWeiboUserInfo() throws IOException, JSONException {
		return getRequestFromUrl(GET_WEIBO_USER_INFO_URL);
	}

	public static void main(String[] args) throws Exception {

		// 请求示例 url 默认请求参数已经做URL编码
//		String url = "http://api01.bitspaceman.com:8000/post/weibo?apikey=EFoPstgagLRjbS786RbROmuFVau9XZDcoJ1uN2H3UxOADz7EzzjWVMypWKbTsP5x&uid=1254461195";
//		String url = "http://api01.bitspaceman.com:8000/profile/weibo?apikey=EFoPstgagLRjbS786RbROmuFVau9XZDcoJ1uN2H3UxOADz7EzzjWVMypWKbTsP5x&id=1254461195&type=1";
		JSONObject json = getTimiSinaWeibo();
		System.out.println(json);
//		JSONArray dataJsonArray = json.getJSONArray("data");
//		if (dataJsonArray.size() > 0) {
//			JSONObject dataObject = dataJsonArray.getJSONObject(0);
//			String timestamp = dataObject.getString("updateDate");
//			Date updateDate = new Date(Long.parseLong(timestamp + "000"));
//			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateDate));
//		}
	}
}
