package org.example.hjycommunity.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * BaseException
 * 基础异常类
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-03 20:50
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7761323336661121608L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    public BaseException() {
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
