package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * MonitorStarterApp
 *
 * @author Yang QingBo
 * @date 2026-06-12 18:43
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient
public class MonitorStarterApp {
	
	public static void main(String[] args) {
		SpringApplication.run(MonitorStarterApp.class, args);
	}

}
