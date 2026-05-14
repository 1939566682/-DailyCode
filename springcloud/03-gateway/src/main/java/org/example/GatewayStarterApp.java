package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * GatewayStarter
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-14 09:11
 */

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(GatewayStarterApp.class,args);
	}
}
