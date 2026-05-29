package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * BeaconCacheClient
 *
 * @author Yang QingBo
 * @date 2026-05-26 09:47
 * @description
 */

@FeignClient("beacon-cache")
public interface BeaconCacheClient {
	
	@GetMapping("/cache/hgetall/{key}")
	Map hGetAll(@PathVariable(value = "key")String key);
	
	
}
