package org.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SmsRoleServiceTest
 *
 * @author Yang QingBo
 * @date 2026-06-17 20:08
 * @description
 */

@SpringBootTest
class SmsRoleServiceTest {
	
	@Autowired
	private SmsRoleService smsRoleService;
	
	@Test
	void getRoleName() {
		Set<String> set = smsRoleService.getRoleName(1L);
		System.out.println(set);
	}
}
