package org.example.execption;

import lombok.Getter;
import org.example.enums.ExceptionEnums;

/**
 * ApiIllegalException
 * @author Yang QingBo
 * @date 2026-05-29 14:53
 * @description 接口模块的异常对象
 */

@Getter
public class ApiIllegalException extends RuntimeException {
	
	private static final long serialVersionUID = -5348552706306386733L;
	
	private final Integer code;
	
	public ApiIllegalException(String message, Integer code) {
		super(message);
		this.code = code;
	}
	
	
	public ApiIllegalException(ExceptionEnums enums) {
		super(enums.getMessage());
		this.code = enums.getCode();
	}
}
