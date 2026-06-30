package org.example.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * SmsTemp - 短信模板实体
 * TODO: 需确认数据库 sms_temp 表是否已创建，若未创建需先建表
 * 建表SQL参考:
 * CREATE TABLE sms_temp (
 *   id BIGINT NOT NULL AUTO_INCREMENT,
 *   template VARCHAR(500) COMMENT '模板内容',
 *   paramter VARCHAR(200) COMMENT '参数',
 *   creater VARCHAR(50) COMMENT '创建者',
 *   owntype INT DEFAULT 2 COMMENT '创建者类型 1管理员/2普通用户',
 *   status INT DEFAULT 1 COMMENT '状态 0停用/1启用',
 *   created DATETIME COMMENT '创建时间',
 *   updated DATETIME COMMENT '更新时间',
 *   PRIMARY KEY (id)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信模板';
 */
public class SmsTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String template;

    private String paramter;

    private String creater;

    private Integer owntype;

    private Integer status;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getParamter() {
        return paramter;
    }

    public void setParamter(String paramter) {
        this.paramter = paramter == null ? null : paramter.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Integer getOwntype() {
        return owntype;
    }

    public void setOwntype(Integer owntype) {
        this.owntype = owntype;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
