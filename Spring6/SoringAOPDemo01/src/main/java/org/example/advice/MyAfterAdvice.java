package org.example.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 21:43
 */

public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("------后置通知------");
        System.out.println("------后置通知------returnValue------"+returnValue);
        System.out.println("------后置通知------method------"+method);
        System.out.println("------后置通知------args------"+ Arrays.toString(args));
        System.out.println("------后置通知------target------"+target);

    }
}
