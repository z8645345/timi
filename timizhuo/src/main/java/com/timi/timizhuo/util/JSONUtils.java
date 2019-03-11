package com.timi.timizhuo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {

	public static String toJosnString(Object obj) {
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return toJosnString(obj, dateFormat);
	}

	public static String toJosnString(Object obj, String dateFormat) {
		return JSON.toJSONStringWithDateFormat(obj, dateFormat, SerializerFeature.DisableCircularReferenceDetect);
	}
}
