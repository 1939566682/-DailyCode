package org.example.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * TestController
 *
 * @author Yang QingBo
 * @date 2026-06-01 18:33
 * @description
 */
@Slf4j
@RestController
public class TestController {
	
	@Autowired
	private RedisClient redisClient;
	
	// 写测试   hash结构
	@PostMapping("/test/set/{key}")
	public String set(@PathVariable String key, @RequestBody Map map) {
		redisClient.set(key, map);
		return "ok";
	}
	
	// 读测试   hash结构
	@GetMapping("/test/get/{key}")
	public Map get(@PathVariable String key) {
		return redisClient.get(key);
	}
	
	// 管道测试
	@PostMapping("/test/pipeline")
	public String pipeline() {
		Map<String, Object> maps = new HashMap<>();
		maps.put("1888888","北京 电信,移动");
		maps.put("1888889","北京 电信,移动");
		redisClient.pipelined(operations ->
				maps.entrySet().forEach(entry -> {
					operations.opsForValue().set(entry.getKey(), entry.getValue());
		}));
		return "ok";
	}
	
	
	
	
	
	
	
	
	
	
}
