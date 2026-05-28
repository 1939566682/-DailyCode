package org.example.mapper;

import org.example.client.CacheClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClientBalanceMapperTest
 *
 * @author Yang QingBo
 * @date 2026-05-28 20:33
 * @description
 */

@SpringBootTest
class ClientBalanceMapperTest {
	
	@Autowired
	private ClientBalanceMapper clientBalanceMapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	@Test
	void findByClientId() {
		Long balance = clientBalanceMapper.findByClientId(1L);
		System.out.println(balance);
		cacheClient.set("client_balance:clientId",balance);
	}
}
