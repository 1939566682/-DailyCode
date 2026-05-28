package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.CacheClient;
import org.example.entity.ClientSign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClientSignMapperTest
 *
 * @author Yang QingBo
 * @date 2026-05-28 18:06
 * @description
 */

@SpringBootTest
class ClientSignMapperTest {
	
	@Autowired
	private ClientSignMapper clientSignMapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	@Test
	void findByClientId() {
		List<ClientSign> list = clientSignMapper.findByClientId(1L);
		list.forEach(System.out::println);
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map> value = list.stream().map(cs -> {
			try {
				return objectMapper.readValue(objectMapper.writeValueAsString(cs), Map.class);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
		cacheClient.sadd("client_sign:clientId",value.toArray(new Map[]{}));
		
	}
}
