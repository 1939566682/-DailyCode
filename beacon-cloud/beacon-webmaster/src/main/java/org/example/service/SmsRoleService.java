package org.example.service;

import java.util.Set;

/**
 * SmsRoleService
 *
 * @author Yang QingBo
 * @date 2026-06-17 19:35
 * @description
 */

public interface SmsRoleService {
	
	/**
	 * 根据用户id查询角色名称
	 * @param userId
	 * @return
	 */
	Set<String> getRoleName(Long userId);
}
