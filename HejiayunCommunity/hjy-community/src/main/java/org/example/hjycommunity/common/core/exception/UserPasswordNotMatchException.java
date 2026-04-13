package org.example.hjycommunity.common.core.exception;

import java.io.Serial;

/**
 * UserPasswordNotMatchException
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-13 09:53
 */

public class UserPasswordNotMatchException extends CustomException{
	@Serial
	private static final long serialVersionUID = -4403074428321619258L;
	
	public UserPasswordNotMatchException() {
		super(400,"用户不存在或密码错误！");
	}
	
}
