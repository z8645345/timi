package com.timi.timizhuo.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class BeanConvertUtils {

	public static void convert(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}
	
	/*public static void convertList(List sources, List targets) {
		for (Object source : sources) {
			Object target = new Object();
			BeanUtils.copyProperties(source, target);
			targets.add(target);
		}
	}*/
	public static  <T> List<T> convertList(List sources, Class<T> clazz) {
		List<T> targets = new ArrayList<>();
		for (Object source : sources) {
			T target = null;
			try {
				target = clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			BeanUtils.copyProperties(source, target);
			targets.add(target);
		}
		return targets;
	}
}
