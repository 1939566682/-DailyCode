package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 15:12
 */

public class Student {
    private String name;
    private int age;
    private Clazz clazz;

    public Student() {
    }

    public Student(String name, int age, Clazz clazz) {
        this.name = name;
        this.age = age;
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
