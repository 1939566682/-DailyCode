package org.example.execption;

import lombok.Getter;
import org.example.enums.ExceptionEnums;

/**
 * SearchException
 *
 * @author Yang QingBo
 * @date 2026-06-08 15:01
 * @description 搜索模块的异常对象
 */

@Getter
public class SearchException extends RuntimeException {
	
	private static final long serialVersionUID = -6005006313483346796L;
	
	private final Integer code;
	
	public SearchException(String message, Integer code) {
		super(message);
		this.code = code;
	}
	
	
	public SearchException(ExceptionEnums enums) {
		super(enums.getMessage());
		this.code = enums.getCode();
	}
}
