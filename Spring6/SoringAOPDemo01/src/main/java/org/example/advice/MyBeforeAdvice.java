package org.example.advice;

import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 21:11
 */

@Component
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("------前置通知------");
        System.out.println("------前置通知------method------"+method);
        System.out.println("------前置通知------args------"+ Arrays.toString(args));
        System.out.println("------前置通知------target------"+target);
    }
}
