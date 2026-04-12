package org.example.hjycommunity.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.hjycommunity.system.domain.pojo.SysUser;
import org.example.hjycommunity.system.mapper.SysUserMapper;
import org.example.hjycommunity.system.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SysUserServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:34
 */

@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {
	
	@Resource
	private SysUserMapper sysUserMapper;
	
	@Override
	public SysUser selectUserByUserName(String userName) {
		return sysUserMapper.selectUserByUserName(userName);
	}
}
