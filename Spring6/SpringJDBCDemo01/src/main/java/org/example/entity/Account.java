package org.example.entity;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 09:43
 */

import java.util.Date;

/**
 * 用户账户类
 */
public class Account {

    private Integer accountId; // 账户ID，主键
    private String accountName; // 账户名称
    private String accountType; // 账户类型
    private Double money; // 账户金额
    private String remark; // 账户备注
    private Integer userId; // 用户ID，账户所属用户
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间

    public Account() {

    }
    // 构造器中不需要加：账户ID（利用主键自增即可）、创建时间、修改时间（这个时间通过sql操作即可）
    public Account(String accountName, String accountType, Double money,
                   String remark, Integer userId) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.money = money;
        this.remark = remark;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", money=" + money +
                ", remark='" + remark + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}