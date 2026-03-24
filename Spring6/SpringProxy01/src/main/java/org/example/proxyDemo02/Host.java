package org.example.proxyDemo02;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 19:39
 */

public class Host implements Rent {
    @Override
    public Object rent(double money) {
        System.out.println("房东房子租出去了，租金：" + money);
        return new Object();
    }
}
