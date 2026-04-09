package org.example.common;

import lombok.Getter;

/**
 * ResultCode
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:34
 */

@Getter
public enum ResultCode {
    SUCCESS("200", "操作成功"),
    ERROR("500", "操作失败");

    private String code;
    private String message;

    ResultCode() {
    }

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
