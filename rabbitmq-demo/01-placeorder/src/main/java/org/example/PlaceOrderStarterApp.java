package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * PlaceOrderStarterApp
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 10:03
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("org.example.mapper")
public class PlaceOrderStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(PlaceOrderStarterApp.class,args);
	}
}
