package org.example.hjycommunity.system.domain.pojo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * LoginBody
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 19:26
 */

@Data
public class LoginBody implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -1419207037482729302L;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * UUID
	 */
	private String uuid;
}
