package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 15:12
 */

public class Student {
    private String name;
    private int age;
    private Clazz clazz;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", clazz=" + clazz +
                '}';
    }
}
