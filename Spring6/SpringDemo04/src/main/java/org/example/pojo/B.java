package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 16:55
 */

public class B {
    A a;

    public B() {
    }

    public B(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }
    public void setA(A a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "B{" +
                "a=" + a +
                '}';
    }
}
