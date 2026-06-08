package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SearchStarterApp
 *
 * @author Yang QingBo
 * @date 2026-06-07 20:23
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient
public class SearchStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(SearchStarterApp.class, args);
	}
}
