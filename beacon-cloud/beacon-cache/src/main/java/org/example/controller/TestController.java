package org.example.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TestController
 *
 * @author Yang QingBo
 * @date 2026-05-24 19:38
 * @description
 */

@Slf4j
@RestController
public class TestController {
	
	@Autowired
	private RedisClient redisClient;
	
	@PostMapping("/cache/hmset/{key}")
	public void hmset(@PathVariable("key") String key, @RequestBody Map<String, Object> map) {
		log.info("【缓存模块】 - hmset方法 存储key = {}，存储value = {}", key, map);
		redisClient.hSet(key, map);
	}
	
	@PostMapping("/cache/set/{key}")
	public void set(@PathVariable("key") String key, @RequestParam("value") String value) {
		log.info("【缓存模块】 - set方法 存储key = {}，存储value = {}", key, value);
		redisClient.set(key, value);
	}
	
	@PostMapping("/cache/sadd/{key}")
	void sadd(@PathVariable("key") String key, @RequestBody Map<String, Object>... values) {
		log.info("【缓存模块】 - sadd方法 存储key = {}，存储value = {}", key, values);
		redisClient.sAdd(key, values);
	}
	
}
