package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.CacheClient;
import org.example.entity.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ChannelMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-05 16:26
 * @description
 */

@SpringBootTest
class ChannelMapperTest {
	
	@Autowired
	private ChannelMapper channelMapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	@Test
	void findAll() throws JsonProcessingException {
		List<Channel> channels = channelMapper.findAll();
		for (Channel channel : channels) {
			ObjectMapper objectMapper = new ObjectMapper();
			Map map = objectMapper.readValue(objectMapper.writeValueAsString(channel), Map.class);
			cacheClient.hmset("channel:" + channel.getId(),map);
		}
	}
}
