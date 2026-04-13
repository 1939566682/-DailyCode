package org.example.hjycommunity.system.service.impl;

import org.example.hjycommunity.system.mapper.SysMenuMapper;
import org.example.hjycommunity.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SysMenuServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-13 15:23
 */

@Service
public class SysMenuServiceImpl implements SysMenuService {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	/**
	 * 根据用户 id 查询菜单权限
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> selectMenuPermissionsByUserId(Long userId) {
		List<String> list = sysMenuMapper.selectMenuPermissionsByUserId(userId);
		return new HashSet<>(list);
	}
}
