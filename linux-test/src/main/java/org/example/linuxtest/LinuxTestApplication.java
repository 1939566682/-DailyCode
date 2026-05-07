package org.example.linuxtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("org.example.linuxtest.mapper")
@SpringBootApplication
public class LinuxTestApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(LinuxTestApplication.class);
	}
	
	public static void main(String[] args) {
		 SpringApplication.run(LinuxTestApplication.class, args);
	}
}
