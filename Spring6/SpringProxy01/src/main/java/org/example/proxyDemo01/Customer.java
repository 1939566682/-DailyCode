package org.example.proxyDemo01;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 19:32
 */

/**
 * 租客，来租房，找中介
 */
public class Customer {
    public static void main(String[] args) {
        // 找中介  租房：创建中介代理，关联房东
        HouseProxy proxy = new HouseProxy(new Host());
        // 租客对接中介：调用代理的租房方法（传入5000元/月租金）
        Object o = proxy.rentHouse(5000.0);
        System.out.println(o);
    }
}
