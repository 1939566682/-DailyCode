package org.example.hjycommunity.framework.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		// 设置访问源头地址
		corsConfiguration.addAllowedOrigin("*");
		// 设置访问源请求头
		corsConfiguration.addAllowedHeader("*");
		// 设置访问源请求方法
		corsConfiguration.addAllowedMethod("*");
		// 对接口配置跨域设置
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(source);
	}
}
