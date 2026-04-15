package org.example.hjycommunity.framework.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * ResourcesConfig
 * 通用配置
 * @author Yang QingBo
 * {@code @date} 2026-04-13 12:06
 */

@Configuration
public class ResourcesConfig {
	/**
	 * 跨域配置
	 */
	@Bean
	public CorsFilter corsFilter() {
		
		CorsConfiguration config = new CorsConfiguration();
		
		// 允许携带凭证
		config.setAllowCredentials(true);
		
		// 设置访问源头地址
		config.setAllowedOriginPatterns(Collections.singletonList("*"));
		/*config.addAllowedOrigin("http://localhost:8080");   // 前端实际地址
		config.addAllowedOrigin("http://127.0.0.1:8080");*/
//		config.addAllowedOrigin("*"); // 不允许与 allowCredentials=true 共用
		
		// 设置访问源请求头
		config.addAllowedHeader(CorsConfiguration.ALL);
		
		// 设置访问源请求方法
		config.addAllowedMethod(CorsConfiguration.ALL);
		
		// 对接口配置跨域设置
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
