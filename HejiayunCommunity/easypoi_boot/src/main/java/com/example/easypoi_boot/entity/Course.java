package com.example.easypoi_boot.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Course
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 15:57
 */

@ExcelTarget("courses")
@Data
public class Course implements Serializable {

    @Serial
    private static final long serialVersionUID = -6606004916864659525L;

    @Excel(name = "编号")
    private String cid;

    @Excel(name = "订单编号")
    private String orderno;

    @Excel(name = "课程名称")
    private String cname;

    @Excel(name = "简介")
    private String brief;

    @Excel(name = "价格")
    private double price;

}
