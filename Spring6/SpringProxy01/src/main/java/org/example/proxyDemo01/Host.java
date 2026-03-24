package org.example.proxyDemo01;

/**
 * 房东类，实现租房的接口
 */
public class Host implements Rent {
    @Override
    public Object rentHouse(Double price) {
        System.out.println("房东的房子被出租了，租金为" + price + "/月");
        return new Object();
    }
}
