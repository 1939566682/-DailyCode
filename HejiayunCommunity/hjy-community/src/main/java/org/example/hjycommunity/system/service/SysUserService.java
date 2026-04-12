package org.example.hjycommunity.system.service;

import org.example.hjycommunity.system.domain.pojo.SysUser;

/**
 * SysUserService
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:34
 */

public interface SysUserService {
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return: com.msb.hjycommunity.system.domain.SysUser
	 */
	public SysUser selectUserByUserName(String userName);
}
