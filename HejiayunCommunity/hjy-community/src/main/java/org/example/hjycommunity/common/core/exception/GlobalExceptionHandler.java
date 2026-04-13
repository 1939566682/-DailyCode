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
    
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public BaseResponse<Object> BaseExceptionHandler(BaseException e) {
        return BaseResponse.fail(e.getMessage());
    }
    
    /**
     * 业务异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public BaseResponse<Object> CustomExceptionHandler(CustomException e) {
        return BaseResponse.fail(e.getCode()+"",e.getMessage(),e.isSuccess());
    }
}
