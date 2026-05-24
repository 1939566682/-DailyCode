package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CacheStarterApp
 *
 * @author Yang QingBo
 * @date 2026-05-24 18:16
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient
public class CacheStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(CacheStarterApp.class, args);
	}
}
