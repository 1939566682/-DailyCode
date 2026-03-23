package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 16:55
 */

public class A {
    B b;

    public A() {
    }

    public A(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }
    public void setB(B b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + b +
                '}';
    }
}
