package com.example.easypoi_boot.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * LoginUrl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 14:51
 */

@Data
@ExcelTarget("loginUrl")
public class LoginUrl implements Serializable {

    @Serial
    private static final long serialVersionUID = -1404162803142091437L;

    @Excel(name = "用户ID",orderNum = "1")
    private String userId;

    @Excel(name = "请求的类型",orderNum = "2")
    private String type;

    @Excel(name = "访问地址",orderNum = "3")
    private String url;

    public LoginUrl() {
    }

    public LoginUrl(String userId, String type, String url) {
        this.userId = userId;
        this.type = type;
        this.url = url;
    }
}
