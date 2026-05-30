package org.example.execption;

import lombok.Getter;
import org.example.enums.ExceptionEnums;

/**
 * ApiKeyIllegalException
 *
 * @author Yang QingBo
 * @date 2026-05-29 14:53
 * @description
 */

@Getter
public class ApiIllegalException extends RuntimeException {
	
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
