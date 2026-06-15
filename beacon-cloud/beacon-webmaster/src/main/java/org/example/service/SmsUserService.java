package org.example.service;

import org.example.entity.SmsUser;

/**
 * SmsUserService
 *
 * @author Yang QingBo
 * @date 2026-06-15 15:20
 * @description 用户信息的Service
 */

public interface SmsUserService {
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	SmsUser findByUsername(String username);
	
}
