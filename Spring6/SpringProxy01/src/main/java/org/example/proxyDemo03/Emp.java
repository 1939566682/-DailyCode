package org.example.proxyDemo03;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 20:13
 * 【3】代理对象真实要做的事，交给Emp中的方法：必须实现MethodInterceptor接口
 */
public class Emp implements MethodInterceptor {
    // 【6】定义房东：
    private Host host;

    public Emp(Host host) {
        this.host = host;
    }

    /*
        【4】实现MethodInterceptor接口，重写intercept  ---》 拦截方法，拦截器 （相当于jdk动态代理中 Emp的 invoke方法）
        method ： 真实对象要调用的方法
        objects ： 真实对象要调用的方法所需要的参数
         */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("签合同");
        // 【5】调用真实对象的方法：第一个参数需要传入房东  构建房东【6】
        Object obj = method.invoke(host, objects);
        System.out.println("售后服务");
        return obj;
    }
}
