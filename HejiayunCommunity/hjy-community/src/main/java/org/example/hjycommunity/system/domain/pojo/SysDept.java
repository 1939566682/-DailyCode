package org.example.hjycommunity.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import org.example.hjycommunity.common.core.domain.BaseEntity;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门表(SysDept)实体类
 *
 * @author Yang QingBo
 * @since 2026-04-07 09:27:31
 */
public class SysDept extends BaseEntity {
	
	@Serial
	private static final long serialVersionUID = -3721654939639957112L;
	
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
	 * 添加一个父部门名称
	 */
	private String parentName;
	
	/**
	 * 添加一个子部门属性
	 */
	private List<SysDept> children = new ArrayList<>();
	
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
	
	public String getParentName() {
		return parentName;
	}
	
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public List<SysDept> getChildren() {
		return children;
	}
	
	public void setChildren(List<SysDept> children) {
		this.children = children;
	}
	
	public Long getDeptId() {
		return deptId;
	}
	
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getAncestors() {
		return ancestors;
	}
	
	public void setAncestors(String ancestors) {
		this.ancestors = ancestors;
	}
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getLeader() {
		return leader;
	}
	
	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDelFlag() {
		return delFlag;
	}
	
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Override
	public String toString() {
		return "SysDept{" +
				"deptId=" + deptId +
				", parentId=" + parentId +
				", parentName='" + parentName + '\'' +
				", children=" + children +
				", ancestors='" + ancestors + '\'' +
				", deptName='" + deptName + '\'' +
				", orderNum=" + orderNum +
				", leader='" + leader + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", status='" + status + '\'' +
				", delFlag='" + delFlag + '\'' +
				'}';
	}
}
