package org.example;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SmsGatewayStaterApp
 *
 * @author Yang QingBo
 * @date 2026-06-09 19:50
 * @description
 */

@SpringBootApplication
@EnableDynamicThreadPool
@EnableFeignClients
public class SmsGatewayStaterApp {
	public static void main(String[] args) {
		SpringApplication.run(SmsGatewayStaterApp.class, args);
	}
}
