package org.example;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * WebMasterStarterApp
 *
 * @author Yang QingBo
 * @date 2026-06-15 09:41
 * @description
 */
@EnableFeignClients
@SpringBootApplication
@MapperScan("org.example.mapper")
public class WebMasterStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(WebMasterStarterApp.class, args);
	}
}
