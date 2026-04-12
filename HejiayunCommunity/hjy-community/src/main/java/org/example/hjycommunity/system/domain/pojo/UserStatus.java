package org.example.hjycommunity.system.domain.pojo;

import lombok.Getter;

/**
 * UserStatus
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:36
 */

@Getter
public enum UserStatus {
	
	OK("0","正常"),DISABLE("1","停用"),DELETED("2","删除");
	
	private final String code;
	private final String info;
	
	UserStatus(String code, String info) {
		this.code = code;
		this.info = info;
	}
	
}
