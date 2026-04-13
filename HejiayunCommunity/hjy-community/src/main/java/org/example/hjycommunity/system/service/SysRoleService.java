package org.example.hjycommunity.system.service;

import java.util.Set;

/**
 * SysRoleService
 * 角色 业务层
 * @author Yang QingBo
 * {@code @date} 2026-04-13 14:57
 */

public interface SysRoleService {
	
	/**
	 * 根据用户 id 查询用户权限
	 * @param userId
	 * @return
	 */
	Set<String> selectRolePermissionsByUserId(Long userId);
	
}
