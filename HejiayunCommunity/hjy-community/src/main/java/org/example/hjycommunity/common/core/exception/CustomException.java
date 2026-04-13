package org.example.hjycommunity.common.core.exception;

import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletResponse;
import java.io.Serial;

/**
 * CustomException
 * 业务异常基类
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-13 09:09
 */

public class CustomException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 2953630663967986505L;
	
	/**
	 * 状态码
	 */
	private int code;
	
	/**
	 * 是否成功
	 */
	private Boolean success;
	
	/**
	 * 返回数据
	 */
	private T data;
	
	/**
	 * 返回消息
	 */
	private String msg;
	
	public CustomException() {
	}
	
	public CustomException(String message) {
		this.msg = message;
	}
	
	public CustomException(int code, String message) {
		this.code = code;
		this.msg = message;
		this.data = null;
		this.success = HttpServletResponse.SC_OK == code;
	}
	
	public CustomException(int code, Boolean success, String message) {
		this.code = code;
		this.success = success;
		this.msg = message;
	}
	
	public CustomException(int code, Boolean success, T data, String message) {
		this.code = code;
		this.success = success;
		this.data = data;
		this.msg = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	
	public String getMessage() {
		return msg;
	}
	
	
	public void setMessage(String message) {
		this.msg = message;
	}
	
	public Boolean isSuccess() {
		return success;
	}
	
	@Override
	public String toString() {
		return "CustomException{" +
				"code=" + code +
				", success=" + success +
				", data=" + data +
				", message='" + msg + '\'' +
				'}';
	}
}
