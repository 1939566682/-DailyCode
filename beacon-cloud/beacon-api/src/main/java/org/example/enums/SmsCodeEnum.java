package org.example.enums;

import lombok.Getter;

/**
 * SmsCodeEnum
 *
 * @author Yang QingBo
 * @date 2026-05-24 17:05
 * @description 一些响应信息中code和msg的对应
 */

@Getter
public enum SmsCodeEnum {
	PARAMETER_ERROR(-1,"参数不合法");
	
	private Integer code;
	private String msg;
	
	SmsCodeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
