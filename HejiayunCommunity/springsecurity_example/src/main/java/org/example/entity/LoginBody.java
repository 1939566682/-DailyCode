package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * LoginBody
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 17:30
 */

@Setter
@Getter
public class LoginBody implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -1787574198836826532L;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 用户密码
	 */
	private String password;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * 唯一标识
	 */
	private String uuid = "";
	
}
