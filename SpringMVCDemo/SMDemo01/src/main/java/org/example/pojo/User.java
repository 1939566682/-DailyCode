package org.example.pojo;

import java.util.Arrays;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-26 16:27
 */

public class User {
    private String name;
    private Integer age;
    private String pwd;
    private String sex;
    private String[] hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("name的setter方法");
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("age的setter方法");
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        System.out.println("pwd的setter方法");
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        System.out.println("sex的setter方法");
        this.sex = sex;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        System.out.println("hobby的setter方法");
        this.hobby = hobby;
    }

    public User() {
        System.out.println("空构造器");
    }

    public User(String name, Integer age, String pwd, String sex, String[] hobby) {
        System.out.println("有参构造器");
        this.name = name;
        this.age = age;
        this.pwd = pwd;
        this.sex = sex;
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby=" + Arrays.toString(hobby) +
                '}';
    }
}