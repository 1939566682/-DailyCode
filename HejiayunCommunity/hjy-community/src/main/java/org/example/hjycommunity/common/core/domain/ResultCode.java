package org.example.hjycommunity.common.core.domain;

import lombok.Getter;

/**
 * 响应状态码枚举类
 * @author Yang QingBo
 * {@code @date} 2026-04-03 19:47
 */


@Getter
public enum ResultCode {
    SUCCESS("200","操作成功"),
    ERROR("500","操作失败");

    private String code;
    private String message;

    ResultCode() {
    }

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}



