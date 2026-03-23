package org.example;

import org.example.factory.PetStore;
import org.example.pojo.Animal;
import org.example.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 14:09
 */

public class Main {
    public static void main(String[] args) {
        ApplicationContext  context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean(User.class);
        System.out.println(user);
        user.sleep();


    }
}