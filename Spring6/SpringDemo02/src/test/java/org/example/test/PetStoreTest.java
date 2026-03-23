package org.example.test;

import org.example.factory.PetStore;
import org.example.pojo.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 14:41
 */

public class PetStoreTest {
    @Test
    public void test1(){
        PetStore p = new PetStore();
        Animal cat = p.getAnimal("cat");
        Animal dog = p.getAnimal("dog");
        System.out.println(cat);
        System.out.println(dog);

        Animal cat1 = PetStore.getAnimal2("cat");
        Animal dog1 = PetStore.getAnimal2("dog");
        System.out.println(cat1);
        System.out.println(dog1);
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Animal cat = (Animal) context.getBean("cat");
        Animal dog = (Animal) context.getBean("dog");
        System.out.println(cat);
        System.out.println(dog);
    }

}
