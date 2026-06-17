package org.example.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SmsMenuMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-17 11:50
 * @description
 */

@SpringBootTest
class SmsMenuMapperTest {
	
	@Autowired
	private SmsMenuMapper menuMapper;
	
	@Test
	void findMenuByUserId() {
		List<Map<String, Object>> list = menuMapper.findMenuByUserId(1L);
		list.forEach(System.out::println);
	}
}
