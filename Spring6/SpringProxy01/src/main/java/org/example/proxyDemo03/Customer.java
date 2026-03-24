package org.example.proxyDemo03;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 20:18
 * 【7】 定义租户,租户租房 ：在main方法中写逻辑：
 */
public class Customer {
    public static void main(String[] args) {
        // 租户找代理
        // 【8】创建增强子 ，通过这个增强子对象的create方法可以构建代理对象：
        Enhancer en = new Enhancer();
        // 【9】利用增强子设置代理对象的父类---》将房东类作为父类
        en.setSuperclass(Host.class);
        // 【10】设置回调  调用Emp中的拦截方法
        en.setCallback(new Emp(new Host()));
        // 【11】利用增强子创建代理对象,这个代理对象是房东的子类，所以可以用房东接收：
        Host proxy = (Host)en.create();
        // 【12】有了代理，就可以租房调用方法了：
        proxy.rent(5000);// 【13】点入rent方法，发现只有真实的房东的方法 ，---》明白为什么叫增强子了

    }
}
