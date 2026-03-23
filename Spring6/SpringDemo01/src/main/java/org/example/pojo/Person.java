package org.example.pojo;


import java.io.Serializable;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 09:23
 */

@
public class Person implements Serializable {
    private String name;
    private int age;
    private double height;

    public Person() {
        System.out.println("空参构造器");
    }

    public Person(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
