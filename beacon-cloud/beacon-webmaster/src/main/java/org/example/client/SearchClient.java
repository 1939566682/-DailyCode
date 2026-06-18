package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;

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
	
	Map<String,Object> findSmsByParams(Map<String,Object> params);

}
