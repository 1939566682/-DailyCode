package com.example.easypoi_boot.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.lang.annotation.Target;

/**
 * Card
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 13:54
 */

@Data
@ExcelTarget("card")
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 8857817019694482281L;

    @Excel(name = "身份证号",orderNum = "8",width = 30)
    private String id;

    @Excel(name = "家庭住址",orderNum = "9")
    private String address;



    public Card(String id, String address) {
        this.id = id;
        this.address = address;
    }

    public Card() {
    }
}
