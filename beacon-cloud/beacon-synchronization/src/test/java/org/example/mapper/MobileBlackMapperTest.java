package org.example.mapper;

import org.example.client.CacheClient;
import org.example.entity.MobileBlack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MobileBlackMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-03 19:58
 * @description
 */

@SpringBootTest
class MobileBlackMapperTest {
	
	@Autowired
	private MobileBlackMapper mobileBlackMapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	@Test
	void findAll() {
		List<MobileBlack> mobileBlackList = mobileBlackMapper.findAll();
		for (MobileBlack mobileBlack : mobileBlackList) {
			if (mobileBlack.getClientId() == 0) {
				// 平台级别的黑名单  black:手机号   作为key，value存储1
				cacheClient.set("black:" + mobileBlack.getBlackNumber(), "1");
			} else {
				// 客户级别的黑名单  black:clientId:手机号   作为key，value存储1
				cacheClient.set("black:" + mobileBlack.getClientId() + ":" + mobileBlack.getBlackNumber(), "1");
			}
			
		}
	}
}
