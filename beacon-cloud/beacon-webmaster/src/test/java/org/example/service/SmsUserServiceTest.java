package org.example.service;

import org.example.entity.SmsUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SmsUserServiceTest
 *
 * @author Yang QingBo
 * @date 2026-06-15 15:27
 * @description
 */

@SpringBootTest
class SmsUserServiceTest {
	
	@Autowired
	private SmsUserService smsUserService;
	
	@Test
	void findByUsername() {
		SmsUser admin = smsUserService.findByUsername("admin");
		System.out.println(admin);
	}
}
