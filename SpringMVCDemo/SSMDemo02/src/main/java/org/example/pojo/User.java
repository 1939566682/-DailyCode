package org.example.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-27 17:42
 */

public class User {

    // 主键ID，自增
    private Integer id;
    // 用户名
    private String name;
    // 年龄
    private Integer age;
    // 出生日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFromat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthdate;
    // 文件名
    private String filename;
    // 文件类型
    private String filetype;

    /**
     * 无参构造（MyBatis 必须）
     */
    public User() {
    }

    /**
     * 全参构造（方便快速创建对象）
     */
    public User(Integer id, String name, Integer age, LocalDateTime birthdate, String filename, String filetype) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthdate = birthdate;
        this.filename = filename;
        this.filetype = filetype;
    }

    // Getter 和 Setter 方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    /**
     * toString 方法（方便日志打印、调试）
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthdate=" + birthdate +
                ", filename='" + filename + '\'' +
                ", filetype='" + filetype + '\'' +
                '}';
    }
}