package org.example.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

/**
 * KaptchaNotMatchException
 * 自定义验证码错误异常
 * @author Yang QingBo
 * {@code @date} 2026-04-12 16:51
 */

public class KaptchaNotMatchException extends AuthenticationException {
	
	@Serial
	private static final long serialVersionUID = 8044361147178713239L;
	
	public KaptchaNotMatchException(String msg) {
		super(msg);
	}
	
	public KaptchaNotMatchException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
