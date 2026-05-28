package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 短信签名认证表(ClientSign)实体类
 *
 * @author Yang QingBo
 * @since 2026-05-26 17:20:21
 */
@Setter
@Getter
public class ClientSign implements Serializable {
    private static final long serialVersionUID = -47753978689562212L;
/**
     * 主键
     */
    private Long id;
/**
     * 客户id，对应client_business表
     */
    private Long clientId;
/**
     * 短信签名内容
     */
    private String signInfo;
/**
     * 审核是否通过 0-审核中 1-审核不通过 2-审核已通过
     */
    private Integer signState;
/**
     * 模板类型 0-验证码，通知类，1-营销类
     */
    private Integer signType;
/**
     * 业务网址与场景
     */
    private String businessWeb;
/**
     * 证明文件描述 如：公司营业执照，APP：应用商店APP管理后台截屏，网站名：ICP备案证明，公众号、小程序：微信公众平台管理页面截图，商标：商标注册证书、商标软著权
     */
    private String proveDescr;
/**
     * 证明文件图片链接，多个以,隔开
     */
    private String proveFile;
/**
     * 创建时间，默认系统时间
     */
    private Date created;
/**
     * 创建人id
     */
    private Long createId;
/**
     * 修改时间，默认系统时间
     */
    private Date updated;
/**
     * 修改人id
     */
    private Long updateId;
/**
     * 是否删除 0-未删除 ， 1-已删除
     */
    private Integer isDelete;
/**
     * 备用字段1
     */
    private String extend1;
/**
     * 备用字段2
     */
    private String extend2;
/**
     * 备用字段3
     */
    private String extend3;
/**
     * 备用字段4
     */
    private String extend4;
	
	
	@Override
    public String toString() {
        return "ClientSign{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", signInfo='" + signInfo + '\'' +
                ", signState=" + signState +
                ", signType=" + signType +
                ", businessWeb='" + businessWeb + '\'' +
                ", proveDescr='" + proveDescr + '\'' +
                ", proveFile='" + proveFile + '\'' +
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
