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
	PARAMETER_ERROR(-10, "参数不合法"),
	SNOWFLAKE_OUT_OF_RANGE(-11, "雪花算法的机器ID或服务ID超出最大范围！"),
	SNOWFLAKE_TIME_BACK(-12, "雪花算法的服务器出现时间回拨问题！"),
	HAVE_DIRTY_WORD(-13, "当前短信内容中包含敏感词信息！"),
	BLACK_GLOBAL(-14, "当前手机号为平台黑名单！"),
	BLACK_CLIENT(-15, "当前手机号为客户黑名单！"),
	;
	
	private final Integer code;
	private final String message;
	
	ExceptionEnums(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
