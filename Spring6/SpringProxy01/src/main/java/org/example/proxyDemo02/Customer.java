package org.example.proxyDemo02;

import java.lang.reflect.Proxy;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 19:39
 */

public class Customer {
    public static void main(String[] args) {
        // 【19】准备中介员工：
        Emp emp = new Emp();
        emp.setHost(new Host());
        /*
        【7】租客租房找中介、代理（不是房东） ，得到代理：通过Proxy.newProxyInstance方法
        【8】代理对象利用Rent  proxy做接收
        【9】newProxyInstance方法有三个参数：
        ClassLoader loader---》类加载器，给哪个类做代理需要通过反射去找，反射需要用到类加载器
        Class<?>[] interfaces---》代理类实现的接口
        InvocationHandler---》代理对象真正需要做的事，必须自己指定  -->【10】

        【20】将准备好的emp传入newProxyInstance的第三个参数：
         */
        Rent proxy = (Rent) Proxy.newProxyInstance(Customer.class.getClassLoader(),new Class[]{Rent.class},emp);
        // 【15】代理租房
        proxy.rent(5000);
    }
}
