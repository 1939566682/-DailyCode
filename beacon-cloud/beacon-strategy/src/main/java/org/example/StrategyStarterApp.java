package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * StrategyStarterApp
 *
 * @author Yang QingBo
 * @date 2026-06-01 15:24
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StrategyStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(StrategyStarterApp.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
