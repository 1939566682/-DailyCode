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
	
	UNKNOWN_ERROR(-999, "未知错误！"),
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
	ONE_MINUTE_LIMIT(-16, "一分钟限流规则生效，拒绝发送短信！"),
	ONE_HOUR_LIMIT(-17, "一小时限流规则生效，拒绝发送短信！"),
	NOT_AVAILABLE_CHANNEL(-18, "无可用的通道！"),
	SEARCH_INDEX_ERROR(-19, "添加文档信息失败！"),
	SEARCH_UPDATE_ERROR(-20,"修改文档信息失败！" ),
	
	
	KAPTCHA_ERROR(-100,"验证码错误！" ),
	AUTHENTICATION_ERROR(-101,"用户名或密码错误！" ),
	NOT_LOGIN(-102,"用户未登录！" ),
	USER_MENU_ERROR(-103,"查询用户菜单信息失败！" ),
	SMS_NO_PERMISSION(-104,"当前登录用户无权限查询当前短信信息！" ),
	SAVE_ERROR(-105, "保存数据失败！"),
	UPDATE_ERROR(-106, "修改数据失败！"),
	DELETE_ERROR(-107, "删除数据失败！"),
	QUERY_ERROR(-108, "查询数据失败！"),
	SMS_SEND_ERROR(-21, "短信发送失败！"),
	PASSWORD_ERROR(-109, "密码修改失败！"),
	USER_NOT_FOUND(-110, "用户不存在！"),
	PASSWORD_EMPTY(-111, "新密码不能为空！"),
	;
	
	private final Integer code;
	private final String message;
	
	ExceptionEnums(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
