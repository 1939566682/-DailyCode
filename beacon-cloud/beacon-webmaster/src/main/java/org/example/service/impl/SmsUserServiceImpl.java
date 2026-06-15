package org.example.service.impl;

import org.example.entity.SmsUser;
import org.example.entity.SmsUserExample;
import org.example.mapper.SmsUserMapper;
import org.example.service.SmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SmsUserServiceImpl
 *
 * @author Yang QingBo
 * @date 2026-06-15 15:21
 * @description
 */

@Service
public class SmsUserServiceImpl implements SmsUserService {
	
	@Autowired
	private SmsUserMapper smsUserMapper;
	
	@Override
	public SmsUser findByUsername(String username) {
		SmsUserExample example = new SmsUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<SmsUser> list = smsUserMapper.selectByExample(example);
		
		return list.isEmpty() ? null : list.get(0);
	}
}
