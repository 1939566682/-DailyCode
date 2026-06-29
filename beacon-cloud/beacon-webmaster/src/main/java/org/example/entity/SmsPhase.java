package org.example.entity;

import java.util.Date;

public class SmsPhase {
    private Long id;
    private String phase;
    private Long provId;
    private Long cityId;
    private String provName;
    private String cityName;
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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase == null ? null : phase.trim();
    }

    public Long getProvId() {
        return provId;
    }

    public void setProvId(Long provId) {
        this.provId = provId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName == null ? null : provName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
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
        return "SmsPhase{" +
                "id=" + id +
                ", phase='" + phase + '\'' +
                ", provId=" + provId +
                ", cityId=" + cityId +
                ", provName='" + provName + '\'' +
                ", cityName='" + cityName + '\'' +
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
