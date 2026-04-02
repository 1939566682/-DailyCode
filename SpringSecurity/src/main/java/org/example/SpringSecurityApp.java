package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 08:56
 */

@SpringBootApplication
@MapperScan("org.example.mapper")
public class SpringSecurityApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApp.class, args);
    }
}
