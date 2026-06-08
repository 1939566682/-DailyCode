package org.example.execption;

import lombok.Getter;
import org.example.enums.ExceptionEnums;

/**
 * StrategyException
 * @author Yang QingBo
 * @date 2026-06-03 16:48
 * @description 策略模块的异常对象
 */

@Getter
public class StrategyException extends RuntimeException {
	
	private static final long serialVersionUID = 8232017131002759096L;
	
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
