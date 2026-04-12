package org.example.entity;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * entity
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 16:38
 */

public class CheckCode implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 3593168598037955483L;
	
	@Getter
	private final String code; //验证字符
	private final LocalDateTime expireTime; //过期时间
	
	public CheckCode(String code, int expireTime) {
		this.code = code;
		//返回指定的过期时间
		this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
	}
	
	public CheckCode(String code) {
		//默认验证码 60秒后过期
		this(code, 60);
	}
	
	//是否过期
	public boolean isExpired() {
		return this.expireTime.isBefore(LocalDateTime.now());
	}
	
}
