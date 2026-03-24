package org.example.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 21:43
 */

public class MyAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("------before invoke------");
        Object returnValue = invocation.proceed();
        System.out.println("------after invoke------");
        return returnValue;
    }
}
