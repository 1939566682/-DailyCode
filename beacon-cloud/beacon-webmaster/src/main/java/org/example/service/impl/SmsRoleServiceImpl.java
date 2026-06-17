package org.example.service.impl;

import org.example.mapper.SmsRoleMapper;
import org.example.service.SmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * SmsRoleServiceImpl
 *
 * @author Yang QingBo
 * @date 2026-06-17 19:35
 * @description
 */

@Service
public class SmsRoleServiceImpl implements SmsRoleService {
	
	@Autowired
	private SmsRoleMapper smsRoleMapper;
	
	/**
	 * 根据用户id查询角色名称
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> getRoleName(Long userId) {
		return smsRoleMapper.findRoleNameByUserId(userId);
	}
}
