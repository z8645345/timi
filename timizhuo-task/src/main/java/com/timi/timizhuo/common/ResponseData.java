package com.timi.timizhuo.common;

/**
 * 返回数据
 * @author zengjia
 *
 */
public class ResponseData {

	private static final String SUCCESS_CODE = "200";
	
	private static final String FIAL_CODE = "400";
	
	/**
	 * 200为成功
	 * 400为失败
	 */
	private String code;
	
	/**
	 * 响应提示语
	 */
	private String message;
	
	/**
	 * 返回业务数据
	 */
	private Object data;
	
	public ResponseData() {
		this.code = SUCCESS_CODE;
	}
	
	public ResponseData(Object data) {
		this.code = SUCCESS_CODE;
		this.setData(data);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public void setSuccess() {
		this.code = SUCCESS_CODE;
	}
	
	public void setFial() {
		this.code = FIAL_CODE;
	}
	
	public boolean isSuccess() {
		return SUCCESS_CODE.equals(this.code) ? true : false;
	}
}
