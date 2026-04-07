package com.example.easypoi_boot.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Order
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 13:58
 */

@Data
@ExcelTarget("order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 4938122837024673059L;

    @Excel(name = "订单编号",width = 30)
    private String id;

    @Excel(name = "订单名称",width = 15)
    private String name;

}
