package org.example.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 21:23
 */
// 1. 交给Spring容器管理
@Component
// 2. 核心注解：标记这是一个切面（必须导入 import org.aspectj.lang.annotation.Aspect;）
@Aspect
public class MyAdvice {

    // 前置通知：方法执行前
    @Before("execution(void org.example.service.impl.UserServiceImpl.b(int))")
    public void before() {
        System.out.println("前置通知：方法执行前");
    }

    // 后置通知：方法正常执行后（无异常才执行）
    @AfterReturning("execution(void org.example.service.impl.UserServiceImpl.b(int))")
    public void afterReturning() {
        System.out.println("后置通知：方法正常执行后");
    }

    // 环绕通知：方法执行前后都能拦截（必须写 ProceedingJoinPoint 参数，且要 throws Throwable）
    @Around("execution(void org.example.service.impl.UserServiceImpl.b(int))")
    public Object around(@NonNull ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知：方法执行前");
        // 执行目标方法（必须调用，否则业务代码不执行）
        Object result = joinPoint.proceed();
        System.out.println("环绕通知：方法执行后");
        return result;
    }
    
    /**
     * 异常通知：只有当目标方法抛出异常时才会执行
     * 可以通过 throwing 属性绑定异常对象，获取异常信息
     */
    @AfterThrowing(
            value = "execution(void org.example.service.impl.UserServiceImpl.b(int))",
            throwing = "ex" // 绑定异常参数名，和方法参数名一致
    )
    public void afterThrowing(Exception ex) {
        System.out.println("异常通知：方法抛出异常了！");
        System.out.println("异常信息：" + ex.getMessage());
    }

    // （可选）最终通知：无论方法是否抛异常，都会执行（类似finally）
    @After("execution(void org.example.service.impl.UserServiceImpl.b(int))")
    public void after() {
        System.out.println("最终通知：方法执行完毕（无论是否异常）");
    }
}