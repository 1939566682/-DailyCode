package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
	
	@GetMapping("/cache/hget/{key}/{field}")
	Integer hGetInteger(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field);
	
	@GetMapping("/cache/get/{key}")
	String getString(@PathVariable(value = "key") String key);
	
	@PostMapping("/cache/sinterStr/{key}/{sinterKey}")
	Set<Object> sinterStr(@PathVariable("key") String key, @PathVariable("sinterKey") String sinterKey, @RequestBody String... values);
	
	@GetMapping("/cache/smember/{key}")
	Set sMember(@PathVariable(value = "key") String key);
	
	@PostMapping("/cache/zaddLong/{key}/{scope}/{member}")
	Boolean zAddLong(@PathVariable("key") String key, @PathVariable("scope") Long scope, @PathVariable("member") Long member);
	
	@PostMapping("/cache/zRangeByScoreCount/{key}/{start}/{end}")
	Integer zRangeByScoreCount(@PathVariable("key") String key, @PathVariable("start") Long start, @PathVariable("end") Long end);
	
	@DeleteMapping("/cache/zRemove/{key}/{member}")
	void zRemove(@PathVariable("key") String key,  @PathVariable("member") Long member);
}
