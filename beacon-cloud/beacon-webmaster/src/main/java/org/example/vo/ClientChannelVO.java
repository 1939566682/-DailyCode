package org.example.vo;

import lombok.Data;

/**
 * ClientChannelVO - 客户通道关系VO
 * 字段名严格匹配前端 clientchannel.js
 * 联表查询：corpname←join client_business, channelname←join channel,
 * price←join channel.channelPrice, extendnumber←clientChannelNumber
 */
@Data
public class ClientChannelVO {
    private Long id;
    private String corpname;
    private String extendnumber;
    private Long price;
    private String channelname;
    private Integer isavailable;
    private Long clientId;
    private Long channelId;
}
