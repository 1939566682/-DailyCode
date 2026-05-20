package org.example;

import com.alibaba.spring.beans.factory.annotation.EnableConfigurationBeanBinding;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("org.example.mapper")
public class OrderManagerStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(OrderManagerStarterApp.class,args);
	}
}
