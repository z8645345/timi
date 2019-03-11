package com.timi.timizhuo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {

	public static String toJosnString(Object obj) {
		return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
}
