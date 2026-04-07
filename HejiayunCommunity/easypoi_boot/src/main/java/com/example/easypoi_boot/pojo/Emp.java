package com.example.easypoi_boot.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Emp
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 14:48
 */

@Data
@ExcelTarget("emps")
public class Emp implements Serializable {

    @Serial
    private static final long serialVersionUID = -3344731430758357600L;

    @Excel(name = "编号")
    private String id;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "年龄")
    private String age;

    @Excel(name = "生日",format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @Excel(name = "状态",replace = {"激活_1","未激活_0"})
    private String status;

    @Excel(name = "头像",type = 2)
    private String photo;
}
