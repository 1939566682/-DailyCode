package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.CacheClient;
import org.example.entity.ClientBusiness;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


/**
 * ClientBusinessMapperTest
 *
 * @author Yang QingBo
 * @date 2026-05-26 18:23
 * @description
 */

@SpringBootTest
@RunWith(SpringRunner.class)
class ClientBusinessMapperTest {
	
	@Autowired
	private ClientBusinessMapper clientBusinessMapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	/*
		客户信息：采用hash结构
		key：client_business:apikey
		value：用户信息的json
	 */
	@Test
	void findById() throws JsonProcessingException {
		ClientBusiness cb = clientBusinessMapper.findById(1L);
		ObjectMapper objectMapper = new ObjectMapper();
		Map map = objectMapper.readValue(objectMapper.writeValueAsString(cb), Map.class);
		cacheClient.hmset("client_business:"+cb.getApikey(),map);
	}
}
