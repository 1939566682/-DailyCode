package org.example.proxyDemo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 19:39
 */

public class Emp implements InvocationHandler {
    // 【17】创建租户：
    private Host host;
    // 【18】给host设置值：可以通过构造器，也可以通过setter方法
    public void setHost(Host host) {
        this.host = host;
    }

    /*
    【11】一旦实现InvocationHandler接口，就重写invoke方法
    【12】invoke方法有三个参数：
    proxy   代理对象
    method 真实对象的方法 ---》当前案例中  ：房东里面的rentHouse方法
    args 指的就是【15】中方法的参数 调用代理对象的时候传入的方法的参数
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 【14】在真实对象的方法外可以补充功能：
        System.out.println("看房子");
        System.out.println("签合同");
        // 【13】 调用真实对象的方法：
        // 【16】invoke参数传递：第一个参数：    第二个参数：args参数
        Object o = method.invoke(host, args);
        System.out.println("售后服务");
        return o;
    }
}
