package org.example.advice;

import org.example.enums.ExceptionEnums;
import org.example.execption.ApiKeyIllegalException;
import org.example.util.R;
import org.example.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ApiKeyExceptionHandler
 *
 * @author Yang QingBo
 * @date 2026-05-29 15:37
 * @description
 */

@RestControllerAdvice
public class ApiKeyExceptionHandler {
	
	@ExceptionHandler(ApiKeyIllegalException.class)
	public ResultVO apiKeyIllegalException(ApiKeyIllegalException e) {
		return R.error(e);
	}

}
