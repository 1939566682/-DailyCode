package org.example;

import org.example.pojo.Clazz;
import org.example.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 15:00
 */

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Clazz c1 = context.getBean("c1", Clazz.class);
        Clazz c2 = context.getBean("c2", Clazz.class);
        Clazz c3 = context.getBean("c3", Clazz.class);
        Clazz c4 = context.getBean("c4", Clazz.class);
        Clazz c5 = context.getBean("c5", Clazz.class);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);

        Student stu = context.getBean("stu", Student.class);
        System.out.println(stu);
    }
}