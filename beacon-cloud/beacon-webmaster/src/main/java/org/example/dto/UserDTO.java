package org.example.dto;


import javax.validation.constraints.NotBlank;

/**
 * UserDTO
 *
 * @author Yang QingBo
 * @date 2026-06-15 18:05
 * @description
 */

public class UserDTO {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String captcha;
	
	private Boolean rememberMe = false;
	
	public UserDTO() {
	}
	
	public UserDTO(String username, String password, String captcha, Boolean rememberMe) {
		this.username = username;
		this.password = password;
		this.captcha = captcha;
		this.rememberMe = rememberMe;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCaptcha() {
		return captcha;
	}
	
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	public Boolean getRememberMe() {
		return rememberMe;
	}
	
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}
