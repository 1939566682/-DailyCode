package org.example.hjycommunity.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.example.hjycommunity.common.core.domain.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 部门表(SysDept)实体类
 *
 * @author Yang QingBo
 * @since 2026-04-07 09:27:31
 */
@Setter
@Getter
@TableName("sys_dept")
public class SysDept extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 920778079208549046L;
    /**
     * 部门id
     */
    @TableId
    private Long deptId;
    /**
     * 父部门id
     */
    private Long parentId;
    /**
     * 父部门名称 ( 非表字段 )
     */
    @TableField(exist = false)
    private String parentName;
    /**
     * 子部门集合 ( 非表字段 )
     */
    @TableField(exist = false)
    private List<SysDept> children;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
}

