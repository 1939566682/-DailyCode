package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Map;
import java.util.Set;

/**
 * CacheClientI
 *
 * @author Yang QingBo
 * @date 2026-06-13 20:38
 * @description
 */

@FeignClient(name = "beacon-cache", contextId = "BeaconCacheClient-monitor")
public interface BeaconCacheClient {
	
	@PostMapping("/cache/keys/{pattern}")
	Set<String> keys(@PathVariable("pattern") String pattern);
	
	@GetMapping("/cache/hgetall/{key}")
	Map<String, Object> hGetAll(@PathVariable(value = "key") String key);
	
}
