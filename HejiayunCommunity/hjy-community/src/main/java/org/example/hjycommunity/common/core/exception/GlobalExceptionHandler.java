package org.example.hjycommunity.common.core.exception;

import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler
 * 全局异常处理器
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-03 20:59
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public BaseResponse<Object> BaseExceptionHandler(BaseException e) {
        return BaseResponse.fail(e.getMessage());
    }
}
