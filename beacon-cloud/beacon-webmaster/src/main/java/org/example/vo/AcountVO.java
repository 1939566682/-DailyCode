package org.example.vo;

import lombok.Data;

/**
 * AcountVO - 充值记录VO（注意拼写acount，匹配前端）
 * 字段名严格匹配前端 acount.js
 * 映射：orderid←Entity.id, corpname←联查client_business, createtime←created格式化,
 * paymentorder←paidinfo, paymentinfo←paidinfo, paymentid←extend1
 */
@Data
public class AcountVO {
    private Long id;
    private Long orderid;
    private String corpname;
    private Long paidvalue;
    private String createtime;
    private String paytime;
    private String paymentid;
    private String paymentorder;
    private String paymentinfo;
    private Long clientId;
}
