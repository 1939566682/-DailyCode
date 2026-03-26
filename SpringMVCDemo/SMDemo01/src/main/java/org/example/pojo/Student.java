package org.example.pojo;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-26 18:48
 */

public class Student {
 private int id;
 private String name;

 public Student() {
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }


 @Override
 public String toString() {
  return "Student{" +
          "id=" + id +
          ", name='" + name + '\'' +
          '}';
 }
}