package org.example.controller;

import com.msb.framework.redis.RedisClient;
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

@RestController
public class TestController {
	
	@Autowired
	private RedisClient redisClient;
	
	// 写测试
	@PostMapping("/test/set/{key}")
	public String set(@PathVariable String key, @RequestBody Map<String, String> map) {
		redisClient.hSet(key, map);
		return "ok";
	}
	
	// 读测试
	@GetMapping("/test/get/{key}")
	public Map<String, Object> get(@PathVariable String key) {
		Map<String, Object> result = redisClient.hGetAll(key);
		return result;
	}
	
}
