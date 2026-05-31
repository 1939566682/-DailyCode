package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.CacheClient;
import org.example.entity.ClientBalance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

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
	void findByClientId() throws JsonProcessingException {
		ClientBalance balance = clientBalanceMapper.findByClientId(1L);
		ObjectMapper objectMapper = new ObjectMapper();
		Map map = objectMapper.readValue(objectMapper.writeValueAsString(balance), Map.class);
		cacheClient.hmset("client_balance:1",map);
	}
}
