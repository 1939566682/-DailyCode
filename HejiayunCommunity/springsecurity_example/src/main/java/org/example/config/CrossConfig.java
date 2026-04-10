package org.example.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CrossConfig
 * 跨域资源共享配置类
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-10 18:47
 */

public class CrossConfig implements WebMvcConfigurer {

    /**
     * 设置跨域规则
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") // 设置允许跨域的路径
                .allowedOriginPatterns("*") // 设置允许跨域请求的域名
                .allowCredentials(true) // 是否允许 cookie
                .allowedMethods("*") // 设置允许请求的方式
                .allowedHeaders("*") // 设置允许的 header 属性
                .maxAge(3600); // 跨域允许的时间
    }
}
