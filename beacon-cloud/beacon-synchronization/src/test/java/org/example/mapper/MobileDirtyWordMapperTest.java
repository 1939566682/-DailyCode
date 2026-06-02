package org.example.mapper;

import org.example.client.CacheClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MobileAreaMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-01 19:29
 * @description
 */

@SpringBootTest
class MobileDirtyWordMapperTest {
	
	@Autowired
	private MobileDirtyWordMapper mapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	@Test
	void loadMobileAreaToRedis() {
		List<String> dirtyWords = mapper.findDirtyWord();
		cacheClient.saddStr("dirty_word" , dirtyWords.toArray(new String[]{}));
	}
}
