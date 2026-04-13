package org.example.hjycommunity.framework.exception;

import org.example.hjycommunity.common.core.exception.CustomException;

import javax.security.sasl.AuthenticationException;
import java.io.Serial;

/**
 * CaptchaNotMatchException
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 17:44
 */

public class CaptchaNotMatchException extends CustomException {
	
	@Serial
	private static final long serialVersionUID = 8702330763542036055L;
	
	public CaptchaNotMatchException() {
		super(400,"验证码错误!");
	}
	
}
