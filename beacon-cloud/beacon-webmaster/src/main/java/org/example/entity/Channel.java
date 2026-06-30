package org.example.entity;

import java.util.Date;

public class Channel {
    private Long id;
    private String channelName;
    private Integer channelType;
    private String channelArea;
    private String channelAreaCode;
    private Long channelPrice;
    private Integer channelProtocal;
    private String channelIp;
    private Integer channelPort;
    private String channelUsername;
    private String channelPassword;
    private String channelNumber;
    private Integer isAvailable;
    private Date created;
    private Long createId;
    private Date updated;
    private Long updateId;
    private Byte isDelete;
    private String extend1;
    private String extend2;
    private String extend3;
    private String extend4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getChannelArea() {
        return channelArea;
    }

    public void setChannelArea(String channelArea) {
        this.channelArea = channelArea == null ? null : channelArea.trim();
    }

    public String getChannelAreaCode() {
        return channelAreaCode;
    }

    public void setChannelAreaCode(String channelAreaCode) {
        this.channelAreaCode = channelAreaCode == null ? null : channelAreaCode.trim();
    }

    public Long getChannelPrice() {
        return channelPrice;
    }

    public void setChannelPrice(Long channelPrice) {
        this.channelPrice = channelPrice;
    }

    public Integer getChannelProtocal() {
        return channelProtocal;
    }

    public void setChannelProtocal(Integer channelProtocal) {
        this.channelProtocal = channelProtocal;
    }

    public String getChannelIp() {
        return channelIp;
    }

    public void setChannelIp(String channelIp) {
        this.channelIp = channelIp == null ? null : channelIp.trim();
    }

    public Integer getChannelPort() {
        return channelPort;
    }

    public void setChannelPort(Integer channelPort) {
        this.channelPort = channelPort;
    }

    public String getChannelUsername() {
        return channelUsername;
    }

    public void setChannelUsername(String channelUsername) {
        this.channelUsername = channelUsername == null ? null : channelUsername.trim();
    }

    public String getChannelPassword() {
        return channelPassword;
    }

    public void setChannelPassword(String channelPassword) {
        this.channelPassword = channelPassword == null ? null : channelPassword.trim();
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber == null ? null : channelNumber.trim();
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4 == null ? null : extend4.trim();
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", channelName='" + channelName + '\'' +
                ", channelType=" + channelType +
                ", channelArea='" + channelArea + '\'' +
                ", channelAreaCode='" + channelAreaCode + '\'' +
                ", channelPrice=" + channelPrice +
                ", channelProtocal=" + channelProtocal +
                ", channelIp='" + channelIp + '\'' +
                ", channelPort=" + channelPort +
                ", channelUsername='" + channelUsername + '\'' +
                ", channelPassword='" + channelPassword + '\'' +
                ", channelNumber='" + channelNumber + '\'' +
                ", isAvailable=" + isAvailable +
                ", created=" + created +
                ", createId=" + createId +
                ", updated=" + updated +
                ", updateId=" + updateId +
                ", isDelete=" + isDelete +
                ", extend1='" + extend1 + '\'' +
                ", extend2='" + extend2 + '\'' +
                ", extend3='" + extend3 + '\'' +
                ", extend4='" + extend4 + '\'' +
                '}';
    }
}
