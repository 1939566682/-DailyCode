package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.swing.*;

/**
 * StockStartApp
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-09 19:48
 */

@SpringBootApplication
@EnableDiscoveryClient
public class StockStartApp {
	public static void main(String[] args) {
		SpringApplication.run(StockStartApp.class,args);
	}
}
