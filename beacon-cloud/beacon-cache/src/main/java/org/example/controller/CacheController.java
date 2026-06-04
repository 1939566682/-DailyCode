package org.example.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * TestController
 *
 * @author Yang QingBo
 * @date 2026-05-24 19:38
 * @description
 */

@Slf4j
@RestController
public class CacheController {
	
	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@PostMapping("/cache/hmset/{key}")
	public void hmset(@PathVariable("key") String key, @RequestBody Map<String, Object> map) {
		log.info("【缓存模块】 - hmset方法 存储key = {}，存储value = {}", key, map);
		redisClient.hSet(key, map);
	}
	
	@PostMapping("/cache/set/{key}")
	public void set(@PathVariable("key") String key, @RequestParam("value") String value) {
		log.info("【缓存模块】 - set方法 存储key = {}，存储value = {}", key, value);
		redisClient.set(key, value);
	}
	
	@PostMapping("/cache/sadd/{key}")
	public void sadd(@PathVariable("key") String key, @RequestBody Map<String, Object>... values) {
		log.info("【缓存模块】 - sadd方法 存储key = {}，存储value = {}", key, values);
		redisClient.sAdd(key, values);
	}
	
	@PostMapping("/cache/saddStr/{key}")
	public void saddStr(@PathVariable("key") String key, @RequestBody String... values) {
		log.info("【缓存模块】 - saddStr 存储key = {}，存储value = {}", key, values);
		redisClient.sAdd(key, values);
	}
	
	@GetMapping("/cache/hgetall/{key}")
	public Map<String, Object> hGetAll(@PathVariable(value = "key") String key) {
		log.info("【缓存模块】 - hGetAll方法 获取key = {} 的数据", key);
		Map<String, Object> value = redisClient.hGetAll(key);
		log.info("【缓存模块】 - hGetAll方法 获取key = {} 的数据，获取value = {}", key, value);
		return value;
	}
	
	@GetMapping("/cache/hget/{key}/{field}")
	public Object hGet(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field) {
		log.info("【缓存模块】 - hGet 方法 获取key = {}，field = {} 的数据  ", key, field);
		Object value = redisClient.hGet(key, field);
		log.info("【缓存模块】 - hGet 方法 获取key = {} 的数据，获取value = {}", key, value);
		return value;
	}
	
	@GetMapping("/cache/smember/{key}")
	public Set sMember(@PathVariable(value = "key") String key) {
		log.info("【缓存模块】 - sMember 方法 获取key = {} 的数据  ", key);
		Set<Object> values = redisClient.sMembers(key);
		log.info("【缓存模块】 - sMember 方法 获取key = {} 的数据，获取value = {}", key, values);
		return values;
	}
	
	@PostMapping("/cache/pipeline/string")
	public void pipelineString(@RequestBody Map<String, String> value) {
		log.info("【缓存模块】 - pipelineString 获取到存储的数据 value的长度 = {}的数据", value.size());
		redisClient.pipelined(operations -> value.entrySet().forEach(entry -> {
			operations.opsForValue().set(entry.getKey(), entry.getValue());
		}));
	}
	
	@GetMapping("/cache/get/{key}")
	public Object get(@PathVariable(value = "key") String key) {
		log.info("【缓存模块】 - get 方法 查询key = {} ", key);
		Object value = redisClient.get(key);
		log.info("【缓存模块】 - get 方法 查询key = {} 对应的 value = {}", key, value);
		return value;
	}
	
	@PostMapping("/cache/sinterStr/{key}/{sinterKey}")
	public Set<Object> sinterStr(@PathVariable("key") String key, @PathVariable("sinterKey") String sinterKey, @RequestBody String... values) {
		log.info("【缓存模块】 - sinterStr的交集方法  存储key = {} sinterKey = {} 存储value = {}", key, sinterKey, values);
		// 1、存储数据到set集合
		redisClient.sAdd(key, values);
		// 2、需要将key和sinterKey做交集操作  并拿到返回的set
		Set<Object> result = redisClient.sIntersect(key, sinterKey);
		// 3、将key直接删除
		redisClient.delete(key);
		return result;
	}
	
	@PostMapping("/cache/zaddLong/{key}/{scope}/{member}")
	public Boolean zAddLong(@PathVariable("key") String key, @PathVariable("scope") Long scope, @PathVariable("member") Long member) {
		boolean result = redisClient.zAdd(key, member, scope);
		log.info("【缓存模块】 - zAddLong方法  存储key = {} 存储scope = {} 存储member = {}  存储结果 = {}", key, scope, member, result);
		return result;
	}
	
	@PostMapping("/cache/zRangeByScoreCount/{key}/{start}/{end}")
	public Integer zRangeByScoreCount(@PathVariable("key") String key, @PathVariable("start") Long start, @PathVariable("end") Long end) {
		log.info("【缓存模块】 - zRangeByScoreCount方法  存储key = {} 存储scope = {} 存储member = {}", key, start, end);
		Set<Object> values = redisClient.zRangeByScore(key, start, end);
		if (values != null) {
			return values.size();
		}
		return 0;
	}
	
	@DeleteMapping("/cache/zRemove/{key}/{member}")
	public void zRemove(@PathVariable("key") String key,  @PathVariable("member") Long member) {
		log.info("【缓存模块】 - zRemove方法  删除key = {} 删除member = {}", key, member);
		redisClient.zRemove(key,member);
	}
	
}
