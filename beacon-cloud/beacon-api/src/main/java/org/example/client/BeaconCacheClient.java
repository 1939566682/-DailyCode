package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BeaconCacheClient
 *
 * @author Yang QingBo
 * @date 2026-05-26 09:47
 * @description
 */

@FeignClient(name = "beacon-cache", contextId = "BeaconCacheClient-api")
public interface BeaconCacheClient {
	
	@GetMapping("/cache/hgetall/{key}")
	Map hGetAll(@PathVariable(value = "key")String key);
	
	@GetMapping("/cache/hget/{key}/{field}")
	Object hGet(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field);
	
	@GetMapping("/cache/hget/{key}/{field}")
	List<String> hGetStringList(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field);
	
	@GetMapping("/cache/hget/{key}/{field}")
	String hGetString(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field);
	
	@GetMapping("/cache/smember/{key}")
	Set<Map> sMember(@PathVariable(value = "key") String key);
	
}
