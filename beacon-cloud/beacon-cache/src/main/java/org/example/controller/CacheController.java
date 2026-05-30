package org.example.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * TestController
 *
 * @author Yang QingBo
 * @date 2026-05-24 19:38
 * @description
 */

@Slf4j
@RestController
public class CacheController {
	
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
	public void sadd(@PathVariable("key") String key, @RequestBody Map<String, Object>... values) {
		log.info("【缓存模块】 - sadd方法 存储key = {}，存储value = {}", key, values);
		redisClient.sAdd(key, values);
	}
	
	@GetMapping("/cache/hgetall/{key}")
	public Map<String, Object> hGetAll(@PathVariable(value = "key") String key) {
		log.info("【缓存模块】 - hGetAll方法 获取key = {} 的数据", key);
		Map<String, Object> value = redisClient.hGetAll(key);
		log.info("【缓存模块】 - hGetAll方法 获取key = {} 的数据，获取value = {}", key, value);
		return value;
	}
	
	@GetMapping("/cache/hget/{key}/{field}")
	public Object hGet(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field) {
		log.info("【缓存模块】 - hGet 方法 获取key = {}，field = {} 的数据  ", key, field);
		Object value = redisClient.hGet(key, field);
		log.info("【缓存模块】 - hGet 方法 获取key = {} 的数据，获取value = {}", key, value);
		return value;
	}
	
	@GetMapping("/cache/smember/{key}")
	public Set sMember(@PathVariable(value = "key") String key) {
		log.info("【缓存模块】 - sMember 方法 获取key = {} 的数据  ", key);
		Set<Object> values = redisClient.sMembers(key);
		log.info("【缓存模块】 - sMember 方法 获取key = {} 的数据，获取value = {}", key, values);
		return values;
	}
	
}
