package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Role
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-10 15:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = -7807685662637074549L;

    @TableId
    private Long roleId;


    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 角色状态 0正常,1停用
     */
    private String status;

    /**
     * 删除标志 0存在,1删除
     */
    private String delFlag;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private String remark;
}