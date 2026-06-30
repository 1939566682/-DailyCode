package org.example.entity;

import java.util.Date;

public class SearchParams {
    private Long id;
    private String name;
    private String columnName;
    private Integer type;
    private Integer tOrder;
    private Integer state;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTOrder() {
        return tOrder;
    }

    public void setTOrder(Integer tOrder) {
        this.tOrder = tOrder;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        return "SearchParams{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", columnName='" + columnName + '\'' +
                ", type=" + type +
                ", tOrder=" + tOrder +
                ", state=" + state +
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
