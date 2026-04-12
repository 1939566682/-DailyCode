package org.example.execption;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

/**
 * CaptchaNotMatchException
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 17:44
 */

public class CaptchaNotMatchException extends AuthenticationException {
	
	@Serial
	private static final long serialVersionUID = 8899704410663364963L;
	
	public CaptchaNotMatchException(String msg) {
		super(msg);
	}
	
	public CaptchaNotMatchException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
