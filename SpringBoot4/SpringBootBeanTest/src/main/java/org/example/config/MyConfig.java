package org.example.config;

import org.example.pojo.Clazz;
import org.example.pojo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-29 18:31
 */

@Configuration
public class MyConfig {

    @Bean
    public Student getStudent(Clazz c) {
        Student s = new Student();
        s.setUname("kk");
        s.setAge(18);
        s.setClazz(c);
        return s;
    }

    @Bean
    public Clazz getClazz() {
        Clazz c = new Clazz();
        c.setCid("1001");
        c.setCname("java");
        return c;
    }
}
