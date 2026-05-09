package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * OrderStartApp
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-09 19:59
 */

@SpringBootApplication
@EnableDiscoveryClient
public class OrderStartApp {
	public static void main(String[] args) {
		SpringApplication.run(OrderStartApp.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
