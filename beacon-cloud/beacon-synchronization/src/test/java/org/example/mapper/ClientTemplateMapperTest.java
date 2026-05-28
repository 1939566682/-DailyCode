package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.CacheClient;
import org.example.entity.ClientTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClientTemplateMapperTest
 *
 * @author Yang QingBo
 * @date 2026-05-28 19:48
 * @description
 */

@SpringBootTest
class ClientTemplateMapperTest {
	
	@Autowired
	ClientTemplateMapper clientTemplateMapper;
	
	@Autowired
	CacheClient cacheClient;
	
	@Test
	void findBySignId() throws JsonProcessingException {
		List<ClientTemplate> ct1 = clientTemplateMapper.findBySignId(15L);
		List<ClientTemplate> ct2 = clientTemplateMapper.findBySignId(24L);
		System.out.println(ct1);
		ct1.forEach(System.out::println);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map> value = ct1.stream().map(ct -> {
			try {
				return objectMapper.readValue(objectMapper.writeValueAsString(ct), Map.class);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
		cacheClient.sadd("client_template:15", value.toArray(new Map[]{}));
	}
}
