package org.example.client.fallback;

import org.example.client.StockClient;
import org.springframework.stereotype.Component;

/**
 * StockClientFallback
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-13 18:18
 */

@Component
public class StockClientFallback implements StockClient {
	
	@Override
	public String test() {
		return "test的降级方法 - 服务器正忙 请稍后再试！！";
	}
}
