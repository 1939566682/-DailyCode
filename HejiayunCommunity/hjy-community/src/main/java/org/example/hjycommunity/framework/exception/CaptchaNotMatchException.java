package org.example.hjycommunity.framework.exception;

import javax.security.sasl.AuthenticationException;
import java.io.Serial;

/**
 * CaptchaNotMatchException
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 17:44
 */

public class CaptchaNotMatchException extends AuthenticationException {
	
	@Serial
	private static final long serialVersionUID = 8702330763542036055L;
	
	public CaptchaNotMatchException(String msg) {
		super(msg);
	}
	
	public CaptchaNotMatchException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
