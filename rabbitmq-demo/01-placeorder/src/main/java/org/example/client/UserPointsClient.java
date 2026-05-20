package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ItemStockClient
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 11:07
 */

@FeignClient("userpoints")
public interface UserPointsClient {
	
	@GetMapping("/up")
	void up();
}
