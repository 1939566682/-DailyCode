package org.example.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 客户通道表(ClientChannel)实体类
 *
 * @author Yang QingBo
 * @since 2026-06-05 16:39:13
 */
public class ClientChannel implements Serializable {
    private static final long serialVersionUID = 595313964254045882L;
/**
     * 客户id，对应client_business表
     */
    private Long clientId;
/**
     * 通道id，对应channel表
     */
    private Long channelId;
/**
     * 通道权重
     */
    private Integer clientChannelWeight;
/**
     * 下发扩展号 如：通道接入号为1069886，后面可以扩展数字，最长不超过20位
     */
    private String clientChannelNumber;
/**
     * 是否启动 0-启用中 1-已停用
     */
    private Integer isAvailable;
    
    public Long getClientId() {
        return clientId;
    }
    
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    
    public Long getChannelId() {
        return channelId;
    }
    
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
    
    public Integer getClientChannelWeight() {
        return clientChannelWeight;
    }
    
    public void setClientChannelWeight(Integer clientChannelWeight) {
        this.clientChannelWeight = clientChannelWeight;
    }
    
    public String getClientChannelNumber() {
        return clientChannelNumber;
    }
    
    public void setClientChannelNumber(String clientChannelNumber) {
        this.clientChannelNumber = clientChannelNumber;
    }
    
    public Integer getIsAvailable() {
        return isAvailable;
    }
    
    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }
}
