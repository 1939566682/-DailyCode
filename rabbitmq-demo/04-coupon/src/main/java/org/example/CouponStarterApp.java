package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ItemStockStarterApp
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 10:27
 */

@SpringBootApplication
@EnableDiscoveryClient
public class CouponStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(CouponStarterApp.class,args);
	}
}
