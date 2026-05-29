package org.example.enums;

import lombok.Getter;

/**
 * ExceptionEnums
 *
 * @author Yang QingBo
 * @date 2026-05-29 14:57
 * @description
 */

@Getter
public enum ExceptionEnums {
	
	ILLEGAL_APIKEY(-1, "非法的apikey"),
	IP_NOT_IN_WHITELIST(-2, "请求的ip不在白名单内"),
	NO_AVAILABLE_SIGN(-3, "无可用签名"),
	NO_AVAILABLE_TEMPLATE(-4, "无可用模板"),
	INVALID_MOBILE_FORMAT(-5, "手机号格式不正确"),
	INSUFFICIENT_BALANCE(-6, "客户余额不足"),
	PARAMETER_ERROR(-10, "参数不合法");
	
	private final Integer code;
	private final String message;
	
	ExceptionEnums(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
