package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * CacheClient
 *
 * @author Yang QingBo
 * @date 2026-05-26 19:23
 * @description
 */

@FeignClient("beacon-cache")
public interface CacheClient {
	
	@PostMapping("/cache/hmset/{key}")
	void hmset(@PathVariable("key") String key, @RequestBody Map<String, Object> map);
	
	@PostMapping("/cache/set/{key}")
	void set(@PathVariable("key") String key, @RequestParam("value") Object value);
	
	@PostMapping("/cache/sadd/{key}")
	void sadd(@PathVariable("key") String key, @RequestBody Map<String, Object>... maps);
	
}
