package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Dept)实体类
 *
 * @author Yang QingBo
 * @since 2026-09-08 16:59:00
 */
@Data
public class Dept implements Serializable {
    private static final long serialVersionUID = -84445797158592530L;

    private Integer deptno;

    private String dname;

    private String loc;


    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

}
