package org.example.advice;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 22:14
 */

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 定义MyAspectJAdvice类之后，不用去考虑实现接口的问题
 * 你把不同通知的逻辑，定义到不同方法中即可：
        */
public class MyAspectJAdvice {
    // 定义前置通知的方法：

    public void before(int n){
        System.out.println("------前置通知-------" + n);
    }

    // 定义后置通知的方法：
    public void after(int n){
        System.out.println("------后置通知-------" + n);
    }
    // 定义异常通知的方法：
    public void mythrow(int n,Exception ex){
        System.out.println("------异常通知-------,当前异常的类型为：" + ex.getClass().getName());
    }

    /*
     环绕通知：
     必须加入参数：ProceedingJoinPoint类型的 ---》 因为通过这个参数我们可以获取到切点方法
     这个类型在aop的命名空间依赖包下，所以依赖scope要把runtime去除
     */
    public Object around(ProceedingJoinPoint p, int n) throws Throwable {
        System.out.println("-----环绕通知的前置通知----" + n);
        // 执行切点方法：
        Object o = p.proceed();
        return o;
    }
}
