package org.example.proxyDemo01;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 19:27
 */

/**
 * 代理实现租房接口
 */
public class HouseProxy implements Rent {

    // 定义被代理的对象--->房东
    private Host host;

    // 构造器：传入房东对象初始化代理
    public HouseProxy(Host host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "HouseProxy{" +
                "host=" + host +
                '}';
    }

    @Override
    public Object rentHouse(Double price) {
        // 代理扩展的额外功能（租房前）
        kanfang();    // 带客户看房
        yijia();      // 讨价还价
        chajia(price);// 赚差价（租金20%）

        // 调用房东的核心租房方法（仅传递80%租金给房东）
        Object house = host.rentHouse(price * 0.8);

        // 代理扩展的额外功能（租房后）
        baojie();     // 售后保洁

        return house;
    }

    // 私有辅助方法：仅代理内部使用，封装额外功能
    private void baojie() {
        System.out.println("售后服务：对房子提供保洁服务");
    }

    private void chajia(double money) {
        System.out.println("中间商赚差价" + money * 0.2);
    }

    private void yijia() {
        System.out.println("和客户讨价还价，保障客户的权益，保障房东的权益");
    }

    private void kanfang() {
        System.out.println("带客户看房子");
    }
}
