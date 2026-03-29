package org.example.config;

import org.example.interceptor.DemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-29 18:36
 */

@Configuration  // 类上有注解@Configuration,此类相当于SpringMVC配置文件。
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private DemoInterceptor demoInterceptor;

    //配置拦截器的映射
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        InterceptorRegistration ir = registry.addInterceptor(demoInterceptor);  // 代表注册拦截器
//        InterceptorRegistration ir2 = ir.addPathPatterns("/**");  // 设置拦截路径
//        InterceptorRegistration ir3 = ir2.excludePathPatterns("/login"); // 设置不拦截url

        // 链式调用
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
