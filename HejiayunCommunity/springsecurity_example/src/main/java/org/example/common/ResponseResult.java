package org.example.common;

import lombok.Data;

/**
 * ResponseResult
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:31
 */

@Data
public class ResponseResult<T> {
    private String code;
    private String message;
    private T data;

    public static <T> ResponseResult<T> success(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(null);
        return result;
    }

    public static <T> ResponseResult<T> error(){
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setData(null);
        return result;
    }

    public static <T> ResponseResult<T> error(String code, String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }


}
