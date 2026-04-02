package org.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 08:51
 */

public class Main {
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        System.out.println(encoder.matches("123456", encoder.encode("123456")));
        System.out.println(encoder.matches("123456", "$2a$10$iNJM/C8w0OnCXzqQHr7ul.5pt8.GohKT3fKqOAJPNxJ9zinCLZ83i"));
        System.out.println("----------------");
        System.out.println(encoder.encode("root"));
        System.out.println(encoder.matches("root", encoder.encode("root")));
        System.out.println(encoder.matches("root", "$2a$10$EulOVbopP2tqZyUF6tNKquaDBtax1Zv0eJ87b3wUGFHMrz7CTEevK"));
    }
}