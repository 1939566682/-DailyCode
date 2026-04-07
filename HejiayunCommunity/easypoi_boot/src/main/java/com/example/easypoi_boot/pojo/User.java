package com.example.easypoi_boot.pojo;

import cn.afterturn.easypoi.excel.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 13:47
 */

@Data
@ExcelTarget("users")   // 标识当前类是用于 Excel 的导入导出
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 6222176558369919436L;

    @Excel(name = "编号", orderNum = "1")
    private String id;

    @Excel(name = "用户名", orderNum = "2")
    private String userName;

    @Excel(name = "用户密码", orderNum = "3")
    private String password;

    @Excel(name = "年龄", orderNum = "4", suffix = "岁")
    private Integer age;

    @Excel(name = "生日", orderNum = "5", width = 20.0, exportFormat = "yyyy-MM-dd")
    private Date birthday;

    @Excel(name = "状态",orderNum = "6" ,replace = {"未激活_0","激活_1"})
    private String status; // 0 未激活 1 激活

    @Excel(name = "爱好",orderNum = "7",width = 20)
    private List<String> hobbyList;

//    @ExcelIgnore
    @ExcelEntity(name = "身份信息")
    private Card card;

//    @ExcelIgnore
    @ExcelCollection(name = "订单信息",orderNum = "10")
    private List<Order> orderList;

    @Excel(name = "头像信息",type = 2)
    private String photo;

}
