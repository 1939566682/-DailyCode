package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GatewayRoutesConfig
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-14 09:43
 */

@Configuration
public class GatewayRoutesConfig {
	
//	@Bean
	public RouteLocator pathRoute(RouteLocatorBuilder routeLocatorBuilder) {
		// 基于 RouteLocatorBuilder 获取构建 router 的 builder
		RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
		// 设置 router
//		return routes.route("pathRoute", router -> router.path("/order/**").uri("http://localhost:9090/")).build();
		return routes.route("path", router -> router.path("/order/**").uri("lb://order")).build();
		/*
			routes:
			  - id: path
			    uri: http://localhost:9090/
			    # 当请求满足 predicate 断言后将请求路由到 uri
			    predicates:
			      - Path=/order/**
		 */
	}
	
}
