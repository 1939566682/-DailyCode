package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 15:06
 */

public class Clazz {
    private int cid;
    private String cname;

    public Clazz() {
        System.out.println("Clazz constructor");
    }

    public Clazz(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
        System.out.println("Clazz constructor");
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
