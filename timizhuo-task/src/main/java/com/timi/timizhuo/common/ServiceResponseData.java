package com.timi.timizhuo.common;

/**
 * 业务逻辑层返回数据类
 * @author zengjia
 */
public class ServiceResponseData<T> {
	
	/**
	 * 成功
	 */
	public static final String SUCCESS = "SUCCESS";
	
	/**
	 * 失败
	 */
	public static final String FAIL = "FAIL";

	/**
	 * 返回状态
	 * SUCCESS:成功
	 * FAIL:失败
	 */
	private String status = SUCCESS;
	
	/**
	 * 响应提示语
	 */
	private String message;
	
	/**
	 * 返回业务数据
	 */
	private T data;
	
	/**
	 * 设置为成功状态
	 */
	public void setSuccess() {
		this.status = SUCCESS;
	}
	
	/**
	 * 设置为失败状态
	 */
	public void setFail() {
		this.status = FAIL;
	}
	
	/**
	 * 是否成功
	 * @return
	 */
	public boolean isSuccess() {
		if (SUCCESS.equals(this.status)) {
			return true;
		}
		return false;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
