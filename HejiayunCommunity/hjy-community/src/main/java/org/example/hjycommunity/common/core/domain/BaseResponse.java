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

@Data
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
    private String message;

    /**
     * 返回的数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * 无数据成功返回
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success() {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setCode(ResultCode.SUCCESS.getCode());
        baseResponse.setMessage(ResultCode.SUCCESS.getMessage());
        baseResponse.setData(null);
        return baseResponse;
    }

    /**
     * 有数据成功返回
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setData(data);
        baseResponse.setCode(ResultCode.SUCCESS.getCode());
        baseResponse.setMessage(ResultCode.SUCCESS.getMessage());
        return baseResponse;
    }

    /**
     * 失败返回
     * @param message
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> fail(String message) {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setCode(ResultCode.ERROR.getCode());
        baseResponse.setMessage(message);
        baseResponse.setData(null);
        return baseResponse;
    }

    /**
     * 失败返回
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> fail(String code, String message) {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        baseResponse.setData(null);
        return baseResponse;
    }



}
