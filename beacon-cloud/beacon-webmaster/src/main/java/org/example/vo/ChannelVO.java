package org.example.vo;

import lombok.Data;

/**
 * ChannelVO - 通道VO
 * 字段名严格匹配前端 channel.js
 * 映射差异：channelname←channelName, channeltype←channelType,
 * spnumber←channelNumber(DB列channel__number双下划线), protocaltype←channelProtocal,
 * channelarea←channelArea, channelprice←channelPrice, isavailable←isAvailable
 */
@Data
public class ChannelVO {
    private Long id;
    private String channelname;
    private Integer channeltype;
    private String spnumber;
    private Integer protocaltype;
    private String channelarea;
    private Long channelprice;
    private Integer isavailable;
}
