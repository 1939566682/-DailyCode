package org.example.filters;

/**
 * ParamterInvalidateFilter
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-14 20:44
 */

import org.reflections.util.Utils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 实现Gateway的自定义过滤器  并实现参数校验
 */
@Component
public class ParameterInvalidateFilter implements GlobalFilter, Ordered {
	
	// 要求必须传入一个参数  key=value  要求value不许为空  不许为空串  否则400
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String value = exchange.getRequest().getQueryParams().getFirst("key");
		System.out.println(value);
		if (Utils.isEmpty(value)){
			System.out.println("参数异常！！！");
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
			// 拦截住  不继续往下走
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}
	
	/**
	 * @return 返回的数字越小  优先级越高
	 */
	@Override
	public int getOrder() {
		return 0;
	}
}
