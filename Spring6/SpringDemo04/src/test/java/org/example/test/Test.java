package org.example.test;

import org.example.pojo.A;
import org.example.pojo.B;
import org.example.pojo.Person;
import org.example.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 16:28
 */


public class Test {

    @org.junit.jupiter.api.Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student stu = context.getBean("stu", Student.class);
        System.out.println(stu);
    }

    @org.junit.jupiter.api.Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext02.xml");

        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }

    @org.junit.jupiter.api.Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext03.xml");

        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
    }
}
