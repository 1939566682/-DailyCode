package org.example;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WebMasterStarterApp
 *
 * @author Yang QingBo
 * @date 2026-06-15 09:41
 * @description
 */

@MapperScan("org.example.mapper")
@SpringBootApplication
public class WebMasterStarterApp {
	public static void main(String[] args) {
		SpringApplication.run(WebMasterStarterApp.class, args);
	}
}
