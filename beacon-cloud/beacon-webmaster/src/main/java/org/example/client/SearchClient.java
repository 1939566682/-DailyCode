package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * SearchClient
 *
 * @author Yang QingBo
 * @date 2026-06-18 16:42
 * @description
 */

@FeignClient("beacon-search")
public interface SearchClient {
	
	/**
	 * 去搜索模块查询短信记录
	 * @param params
	 * @return
	 */
	@PostMapping("/search/sms/list")
	Map<String,Object> findSmsByParams(@RequestBody Map<String,Object> params);

}
