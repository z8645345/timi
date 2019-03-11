package com.timi.timizhuo.weibo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.timi.timizhuo.dao.mapper.TimiSinaWeiboMapper;
import com.timi.timizhuo.dao.model.TimiSinaWeibo;
import com.timi.timizhuo.service.TimiImagesService;
import com.timi.timizhuo.util.WeiboUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 卓依婷微博处理类
 * @author 曾佳
 */
@Service
public class WeiboHandle {
	
	private static Logger logger = LoggerFactory.getLogger(WeiboHandle.class);
	
	@Autowired
	private TimiSinaWeiboMapper timiSinaWeiboMapper;

	@Autowired
	private TimiImagesService timiImagesService;

	/**
	 * 根据接口返回的json获得卓依婷新浪微博数据库模型类
	 * @param json
	 * @return
	 * @throws Exception 
	 */
	private TimiSinaWeibo getSinaWeiboModel(JSONObject json) throws Exception {
		JSONArray weiboJsonArray = json.getJSONArray("data");
		JSONObject firstData  = weiboJsonArray.getJSONObject(0);
		TimiSinaWeibo sinaWeiboModel = new TimiSinaWeibo();
		String pDate = firstData.getString("pDate");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sinaWeiboModel.setCreatedAt(df.parse(pDate));
		sinaWeiboModel.setWeiboText(firstData.getString("content"));
		sinaWeiboModel.setOriginalTextUrl(firstData.getString("url"));
		JSONArray imageURLsJsonArray = firstData.getJSONArray("imageURLs");
		if (imageURLsJsonArray != null && imageURLsJsonArray.size() > 0) {
			String originalPics = "";
			for(Object object : imageURLsJsonArray) {
				originalPics += object.toString() + ";";
			}
			sinaWeiboModel.setOriginalPics(originalPics.substring(0, originalPics.length() - 1));
		}
		JSONArray videoURLsJsonArray = firstData.getJSONArray("videoURLs");
		if (videoURLsJsonArray != null && videoURLsJsonArray.size() > 0) {
			String weiboVedio = "";
			for(Object object : videoURLsJsonArray) {
				weiboVedio += object.toString() + ";";
			}
			sinaWeiboModel.setWeiboVedio(weiboVedio.substring(0, weiboVedio.length() - 1));
		}
		JSONObject fromJSONObject = firstData.getJSONObject("from");
		JSONObject extendJSONObject = fromJSONObject.getJSONObject("extend");
		sinaWeiboModel.setTimiPic(extendJSONObject.getString("avatar_large"));
		return sinaWeiboModel;
	}
	
	/**
	 * 根据json获取最新发文时间
	 * @return
	 */
	private Date getNewestWeiboCreateAtByJSON(JSONObject json) {
		JSONArray dataJsonArray = json.getJSONArray("data");
		if (dataJsonArray.size() > 0) {
			JSONObject dataObject = dataJsonArray.getJSONObject(0);
			String timestamp = dataObject.getString("updateDate");
			Date updateDate = new Date(Long.parseLong(timestamp + "000"));
			return updateDate;
		}
		return null;
	}
	
	/**
	 * 卓依婷最新微博处理
	 */
	public void timiWeiboDataHandle() {
		try {
			JSONObject json = WeiboUtils.getTimiWeiboUserInfo();
			Date jsonNewestWeiboCreateAt = getNewestWeiboCreateAtByJSON(json);
			Date newestWeiboCreateAt = getNewestWeiboCreateAt();
			if (isUpdateWeibo(jsonNewestWeiboCreateAt, newestWeiboCreateAt)) {
				JSONObject weiboJson = WeiboUtils.getTimiSinaWeibo();
				TimiSinaWeibo sinaWeiboModel = this.getSinaWeiboModel(weiboJson);
				// 2.有更新微博微信发出通知
//				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String updateTime = df.format(sinaWeiboModel.getCreatedAt());
//				SendWechatMsg.sendMsg("卓依婷微博更新通知", updateTime + " 依婷发布新微博啦", sinaWeiboModel.getWeiboText(),sinaWeiboModel.getOriginalTextUrl());
				// 3.将更新的微博信息同步至数据库
				timiSinaWeiboMapper.insert(sinaWeiboModel);
				timiImagesService.syncWeiboImages();
			}
		} catch (Exception e) {
			logger.error("m:timiWeiboDateHandle 同步微博信息失败", e);
		}
		
	}
	
	/**
	 * 获取数据库最新一条微博发文时间
	 * @return
	 */
	private Date getNewestWeiboCreateAt() {
		TimiSinaWeibo sinaWeiboModel = timiSinaWeiboMapper.findNewestWeibo();
		if (sinaWeiboModel == null) {
			return null;
		}
		return sinaWeiboModel.getCreatedAt();
	}
	
	/**
	 * 判断卓依婷是否更新微博
	 * @return true：更新了 false：未更新
	 */
	private boolean isUpdateWeibo(Date jsonNewestWeiboCreateAt, Date newestWeiboCreateAt) {
		if (jsonNewestWeiboCreateAt == null) {
			return false;
		}
		if (newestWeiboCreateAt == null) {
			return true;
		}
		if (jsonNewestWeiboCreateAt.after(newestWeiboCreateAt)) {
			return true;
		}
		return false;
	}
}
