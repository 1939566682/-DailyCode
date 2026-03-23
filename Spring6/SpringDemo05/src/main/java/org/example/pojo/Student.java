package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 15:12
 */

public class Student {
    private Clazz clazz;

    public Student() {
    }

    public Student(Clazz clazz) {
        this.clazz = clazz;
        System.out.println("Clazz constructor");
    }


    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }


    @Override
    public String toString() {
        return "Student{" +
                "clazz=" + clazz +
                '}';
    }
}
