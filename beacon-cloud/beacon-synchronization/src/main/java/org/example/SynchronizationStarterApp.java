package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SynchronizationStarterApp
 *
 * @author Yang QingBo
 * @date 2026-05-26 17:07
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("org.example.mapper")
public class SynchronizationStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(SynchronizationStarterApp.class, args);
	}
}
