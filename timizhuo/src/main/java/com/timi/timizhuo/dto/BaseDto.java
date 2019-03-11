package com.timi.timizhuo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据传输对象对类
 * @author zengjia
 */
@Data
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页数
	 */
	private int pageNum;

	/**
	 * 每条条数
	 */
	private int pageSize;
}
