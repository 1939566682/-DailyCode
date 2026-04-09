package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * BCryptPasswordEncoderTest
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:21
 */

@SpringBootTest
public class BCryptPasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testBcryp() {

        String e1 = passwordEncoder.encode("123456");
        String e2 = passwordEncoder.encode("123456");
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e1.equals(e2));

        //$2a$10$0CS95XYw7GyDQNXq6FO7FuWDHR4yLTVyFXgQICjgTddWIG9OJ6isy
        boolean b = passwordEncoder.matches("123456",
                "$2a$10$980ouoAQu9Y1kHSoXKrjaO3p3JKhRJDVlkwtmNLH2WS8uQjx7YZJK");

        System.out.println("=============== " + b);
    }
}
