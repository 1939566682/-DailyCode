package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * BeaconCacheClient
 *
 * @author Yang QingBo
 * @date 2026-06-01 16:18
 * @description
 */

@FeignClient(name = "beacon-cache", contextId = "BeaconCacheClient-strategy")
public interface BeaconCacheClient {
	
	@GetMapping("/cache/hget/{key}/{field}")
	String hGet(@PathVariable(value = "key") String key,
	            @PathVariable(value = "field") String field);
	
	@GetMapping("/cache/hget/{key}/{field}")
	Integer hGetInteger(@PathVariable(value = "key") String key,
	                    @PathVariable(value = "field") String field);
	
}
