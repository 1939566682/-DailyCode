package org.example.springdiy;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 09:46
 */

public class MyBean {
    private String id; // bean标签的id属性
    private String clazz; // bean标签class属性

    public MyBean() {
    }


    public MyBean(String id, String clazz) {
        this.id = id;
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
