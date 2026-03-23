package org.example;

import org.example.pojo.Person;
import org.example.pojo.User;
import org.example.springdiy.MyClassPathXmlApplicationContext;
import org.example.springdiy.MyFactory;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 09:41
 */

public class Main {
    public static void main(String[] args) {
        // 创建容器
        MyFactory myFactory = new MyClassPathXmlApplicationContext("applicationContext.xml");

        // 获取Bean
        Person person = (Person) myFactory.getBean("person");
        User user = (User) myFactory.getBean("user");

        // 调用方法
        person.eat();
        user.sleep();

        // 打印对象
        System.out.println(person);
        System.out.println(user);
    }
}