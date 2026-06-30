package org.example.vo;

import lombok.Data;

/**
 * ClientBusinessVO - 客户接入配置VO
 * 字段名严格匹配前端 clientbusiness.js
 * 映射差异：usercode←apikey, pwd←apikey掩码, ipaddress←ipAddress,
 * isreturnstatus←isCallback, receivestatusurl←callbackUrl,
 * priority←extend3, usertype←extend4, state←isDelete反转, mobile←clientPhone, money←联查client_balance
 */
@Data
public class ClientBusinessVO {
    private Long id;
    private String corpname;
    private String usercode;
    private String pwd;
    private String ipaddress;
    private Integer isreturnstatus;
    private String receivestatusurl;
    private Integer priority;
    private Integer usertype;
    private Integer state;
    private String mobile;
    private Long money;
}
