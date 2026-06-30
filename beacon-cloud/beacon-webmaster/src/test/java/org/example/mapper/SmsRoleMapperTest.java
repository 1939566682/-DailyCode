package org.example.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SmsRoleMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-17 20:06
 * @description
 */

@SpringBootTest
class SmsRoleMapperTest {
	
	@Autowired
	private SmsRoleMapper smsRoleMapper;
	
	@Test
	void findRoleNameByUserId() {
		Set<String> set = smsRoleMapper.findRoleNameByUserId(1L);
		System.out.println(set);
	}
}
