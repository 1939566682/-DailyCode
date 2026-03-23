package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 15:06
 */

public class Clazz {
    private int cid;
    private String cname;

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }



    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
