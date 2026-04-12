package org.example.hjycommunity.system.domain.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.hjycommunity.common.core.domain.BaseEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.util.Date;

/**
 * SysUser
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 19:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {
	
	@Serial
	private static final long serialVersionUID = 6261457842453147146L;
	
	/**
	 * 用户ID
	 */
	@Excel(name = "用户序号")
	@TableId
	private Long userId;
	
	/**
	 * 部门ID
	 */
	@Excel(name = "部门编号")
	private Long deptId;
	
	/**
	 * 用户账号
	 */
	@Excel(name = "登录名称")
	private String userName;
	
	/**
	 * 用户昵称
	 */
	@Excel(name = "用户名称")
	private String nickName;
	
	/**
	 * 用户邮箱
	 */
	@Excel(name = "用户邮箱")
	private String email;
	
	/**
	 * 手机号码
	 */
	@Excel(name = "手机号码")
	private String phonenumber;
	
	/**
	 * 用户性别
	 */
	@Excel(name = "用户性别", replace = {"男_0", "女_1", "未知_0"})
	private String sex;
	
	/**
	 * 用户头像
	 */
	private String avatar;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 盐加密
	 */
	private String salt;
	
	/**
	 * 账号状态（0正常 1停用）
	 */
	@Excel(name = "帐号状态", replace = {"正常_0", "停用_1"})
	private String status;
	
	/**
	 * 删除标志（0代表存在 2代表删除）
	 */
	private String delFlag;
	
	/**
	 * 最后登录IP
	 */
	@Excel(name = "最后登录IP")
	private String loginIp;
	
	/**
	 * 最后登录时间
	 */
	@Excel(name = "最后登录时间", width = 30, format = "yyyy-MM-dd HH:mm:ss")
	private Date loginDate;
	
	public SysUser() {
	}
	
	//对 用户名 邮箱 手机号进行校验
	@NotBlank(message = "用户账号不能为空")
	@Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
	public String getUserName() {
		return userName;
	}
	
	@Email(message = "邮箱格式不正确")
	@Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
	public String getEmail() {
		return email;
	}
	
	@Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
	public String getPhonenumber() {
		return phonenumber;
	}
	
	//序列化时忽略密码
	@JsonIgnore
	public String getPassword() {
		return password;
	}
}
