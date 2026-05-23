package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * apiStarterApp
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-23 21:17
 */

@SpringBootApplication
@EnableDiscoveryClient
public class apiStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(apiStarterApp.class, args);
	}
}
