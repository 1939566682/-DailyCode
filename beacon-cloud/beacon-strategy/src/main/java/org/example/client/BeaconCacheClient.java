package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	String hGet(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field);
	
	@GetMapping("/cache/get/{key}")
	String getString(@PathVariable(value = "key") String key);
	
	@PostMapping("/cache/sinterStr/{key}/{sinterKey}")
	Set<Object> sinterStr(@PathVariable("key") String key, @PathVariable("sinterKey") String sinterKey, @RequestBody String... values);
}
