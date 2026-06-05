package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.CacheClient;
import org.example.entity.ClientChannel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClientChannelMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-05 16:42
 * @description
 */

@SpringBootTest
class ClientChannelMapperTest {
	
	@Autowired
    private ClientChannelMapper clientChannelMapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	@Test
	void findAll() throws JsonProcessingException {
		List<ClientChannel> clientChannels = clientChannelMapper.findAll();
		for (ClientChannel clientChannel : clientChannels) {
			ObjectMapper objectMapper = new ObjectMapper();
			Map map = objectMapper.readValue(objectMapper.writeValueAsString(clientChannel), Map.class);
			cacheClient.sadd("client_channel:"+clientChannel.getClientId(),map);
		}
	}
}
