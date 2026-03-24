package org.example.advice;

import org.springframework.aop.ThrowsAdvice;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 21:43
 */

public class MyThrowAdvice implements ThrowsAdvice {
    public void afterThrowing(Throwable ex) throws Throwable {
        System.out.println("------afterThrowing------");
    }
}
