package org.example.proxyDemo03;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 20:12
 *【1】因为cglib动态代理不需要接口，所以无需考虑接口，直接定义房东即可
 */
public class Host {
    // 【2】因为没有接口，房东里面的租房的方法随便写。
    public Object rent(double money){
        System.out.println("房东租房，租金为：" + money);
        return new Object();
    }
}
