package org.example.mapper;

import org.example.entity.SmsUser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SmsUserMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-15 15:14
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class SmsUserMapperTest {
	
	@Autowired
	private SmsUserMapper smsUserMapper;

	@Test
	void findById() {
		SmsUser smsUser = smsUserMapper.selectByPrimaryKey(1);
		System.out.println(smsUser);
	}
	
}
