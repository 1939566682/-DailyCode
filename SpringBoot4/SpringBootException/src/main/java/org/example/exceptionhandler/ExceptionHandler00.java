package org.example.exceptionhandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 08:41
 */

@Configuration
public class ExceptionHandler00 {

    @Bean
    public SimpleMappingExceptionResolver getSMER() {
        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException", "myError");
        smer.setExceptionMappings(properties);
        return smer;

    }

}
