package org.example;

import org.example.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBeanTestApplicationTests {

    @Autowired
    private Student s;

    @Test
    void contextLoads() {
        System.out.println(s);
    }

}
