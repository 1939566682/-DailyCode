package org.example.mapper;

import org.example.client.CacheClient;
import org.example.entity.MobileArea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MobileAreaMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-01 19:29
 * @description
 */

@SpringBootTest
class MobileAreaMapperTest {
	
	@Autowired
	private MobileAreaMapper mapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	@Test
	void loadMobileAreaToRedis() {
		List<MobileArea> list = mapper.findAll();
		int total = list.size();
		System.out.println("总记录数: " + total);
		
		int batchSize = 2000;  // 每批2000条，可根据实际情况调整
		Map<String, String> batch = new HashMap<>(batchSize);

		for (int i = 0; i < total; i++) {
			MobileArea ma = list.get(i);
			String key = "phase:" + ma.getMobileNumber();
			String value = ma.getMobileArea() + "," + ma.getMobileType();
			batch.put(key, value);

			if (batch.size() >= batchSize) {
				cacheClient.pipelineString(batch);
				batch.clear();
				System.out.println("已写入 " + (i + 1) + " 条");
			}
		}
		// 处理最后一批
		if (!batch.isEmpty()) {
			cacheClient.pipelineString(batch);
		}
		System.out.println("全部写入完成");
	}
}
