package org.example.entity;

import java.util.Date;

public class ClientAccountRecord {
    private Long id;
    private Long clientId;
    private Long paidValue;
    private Integer paidState;
    private String paidInfo;
    private Date created;
    private Long createId;
    private Date updated;
    private Long updateId;
    private Byte isDelete;
    private String extend1;
    private String extend2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(Long paidValue) {
        this.paidValue = paidValue;
    }

    public Integer getPaidState() {
        return paidState;
    }

    public void setPaidState(Integer paidState) {
        this.paidState = paidState;
    }

    public String getPaidInfo() {
        return paidInfo;
    }

    public void setPaidInfo(String paidInfo) {
        this.paidInfo = paidInfo == null ? null : paidInfo.trim();
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

    @Override
    public String toString() {
        return "ClientAccountRecord{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", paidValue=" + paidValue +
                ", paidState=" + paidState +
                ", paidInfo='" + paidInfo + '\'' +
                ", created=" + created +
                ", createId=" + createId +
                ", updated=" + updated +
                ", updateId=" + updateId +
                ", isDelete=" + isDelete +
                ", extend1='" + extend1 + '\'' +
                ", extend2='" + extend2 + '\'' +
                '}';
    }
}
