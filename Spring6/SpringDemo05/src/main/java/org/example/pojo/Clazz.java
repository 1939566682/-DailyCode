package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 15:06
 */

public class Clazz {
    private String cname;

    public Clazz() {
    }

    public Clazz(String cname) {
        this.cname = cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }


    @Override
    public String toString() {
        return "Clazz{" +
                "cname='" + cname + '\'' +
                '}';
    }
}
