package org.example.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * CacheController
 *
 * @author Yang QingBo
 * @date 2026-05-26 19:27
 * @description
 */

@Slf4j
@RestController
public class CacheController {
	
	public final RedisClient redisClient;
	
	public CacheController(RedisClient redisClient) {
		this.redisClient = redisClient;
	}
	
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
	
}
