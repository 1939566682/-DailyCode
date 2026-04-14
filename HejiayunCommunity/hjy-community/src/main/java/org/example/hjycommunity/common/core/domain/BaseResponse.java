package org.example.hjycommunity.common.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * 响应结果封装类
 * @author Yang QingBo
 * {@code @date} 2026-04-03 19:51
 */

public class BaseResponse<T>  implements Serializable {
    @Serial
    private static final long serialVersionUID = 6413454222768756364L;

    /**
     * 响应状态码
     */
    private String code;

    /**
     * 响应结果描述
     */
    private String msg;

    /**
     * 返回的数据
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    
    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 无数据成功返回
     */
    public static <T> BaseResponse<T> success() {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setCode(ResultCode.SUCCESS.getCode());
        baseResponse.setMsg(ResultCode.SUCCESS.getMessage());
        baseResponse.setData(null);
        baseResponse.setSuccess(true);
        return baseResponse;
    }

    /**
     * 有数据成功返回
     */
    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setData(data);
        baseResponse.setCode(ResultCode.SUCCESS.getCode());
        baseResponse.setMsg(ResultCode.SUCCESS.getMessage());
        baseResponse.setSuccess(true);
        return baseResponse;
    }

    /**
     * 失败返回
     */
    public static <T> BaseResponse<T> fail(String message) {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setCode(ResultCode.ERROR.getCode());
        baseResponse.setMsg(message);
        baseResponse.setData(null);
        baseResponse.setSuccess(false);
        return baseResponse;
    }

    /**
     * 失败返回
     */
    public static <T> BaseResponse<T> fail(String code, String message) {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setCode(code);
        baseResponse.setMsg(message);
        baseResponse.setData(null);
        baseResponse.setSuccess(false);
        return baseResponse;
    }
    
    /**
     * 失败返回
     */
    public static <T> BaseResponse<T> fail(String code, String message,Boolean success) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMsg(message);
        baseResponse.setData(null);
        baseResponse.setSuccess(success);
        return baseResponse;
    }
    
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
