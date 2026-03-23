package org.example.test;

import org.example.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 17:20
 */

public class Test {
    @org.junit.jupiter.api.Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = context.getBean(Student.class);
        System.out.println(student);
    }
}
