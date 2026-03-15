package org.example.luoYu.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-15 11:08
 */

/*
 * 实体类:
 * 和数据库表格名称和字段是一一对应的类
 * 该类的对象主要用处是存储从数据库中查询出来的数据
 * 除此之外,该类没有任何的其他功能
 * 要求
 * 1 类名和表名保持一致  (见名知意)
 * 2 属性个数和数据库的表的列数保持一致
 * 3 属性的数据类型和列的数据类型保持一致
 * 4 属性名和数据库表格的列名要保持一致
 * 5 所有的属性必须都是私有的 (出于安全考虑)
 * 6 实体类的属性推荐写成包装类
 * 7 日期类型推荐写成java.util.Date
 * 8 所有的属性都要有get和set方法
 * 9 必须具备空参构造方法
 * 10 实体类应当实现序列化接口 (mybatis  分布式 )
 * 11 实体类中其他构造方法可选
 * */
public class Emp implements Serializable {
    @Serial
    private static final long serialVersionUID = -8477172028488703621L;

    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }

    public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Emp(){

    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
