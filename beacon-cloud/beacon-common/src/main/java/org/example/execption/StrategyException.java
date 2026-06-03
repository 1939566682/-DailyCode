package org.example.execption;

import lombok.Getter;
import org.example.enums.ExceptionEnums;

/**
 * StrategyException
 * 策略模块的异常对象
 * @author Yang QingBo
 * @date 2026-06-03 16:48
 * @description
 */

@Getter
public class StrategyException extends RuntimeException {
	private final Integer code;
	
	public StrategyException(String message, Integer code) {
		super(message);
		this.code = code;
	}
	
	
	public StrategyException(ExceptionEnums enums) {
		super(enums.getMessage());
		this.code = enums.getCode();
	}
}
