package org.example.hjycommunity.system.service.impl;

import org.example.hjycommunity.system.mapper.SysRoleMapper;
import org.example.hjycommunity.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SysRoleServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-13 14:57
 */

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper  sysRoleMapper;
	
	/**
	 * 根据用户 id 查询用户权限
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> selectRolePermissionsByUserId(Long userId) {
		List<String> list = sysRoleMapper.selectRolePermissionsByUserId(userId);
		return new HashSet<>(list);
	}
}
