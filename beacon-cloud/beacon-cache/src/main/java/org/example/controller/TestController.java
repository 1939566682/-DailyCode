package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TestController
 *
 * @author Yang QingBo
 * @date 2026-05-24 19:38
 * @description
 */

@RestController
public class TestController {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	// 写测试
	@PostMapping("/test/set/{key}")
	public String set(@PathVariable String key, @RequestBody Map<String, String> map) {
		redisTemplate.opsForHash().putAll(key,map);
		return "ok";
	}
	
	// 读测试
	@GetMapping("/test/get/{key}")
	public Map<Object, Object> get(@PathVariable String key) {
		Map<Object, Object> result = redisTemplate.opsForHash().entries(key);
		return result;
	}
	
}
