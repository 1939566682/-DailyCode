package org.example.hjycommunity.system.domain.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * LoginUser
 * 登录用户 身份权限对象
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:03
 */

@Data
public class LoginUser implements UserDetails {
	
	@Serial
	private static final long serialVersionUID = 7254649366155939530L;
	
	/**
	 * 用户唯一标识
	 */
	private String token;
	
	/**
	 * 用户信息
	 */
	private SysUser user;
	
	/**
	 * 登录时间
	 */
	private Long loginTime;
	
	/**
	 * 过期时间
	 */
	private Long expireTime;
	
	/**
	 * 权限列表
	 */
	private Set<String> permissions;
	
	//一定要有空参构造,否则序列化会失败
	public LoginUser() {
	}
	
	public LoginUser(SysUser user, Set<String> permissions) {
		this.user = user;
		this.permissions = permissions;
	}
	
	public LoginUser(SysUser user) {
		this.user = user;
	}
	
	/**
	 * 获取用户被授予的权限
	 * @return
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}
	
	/**
	 * 获取用户密码 用于密码验证
	 *
	 * @return
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUserName();
	}
	
	/**
	 * 账户是否过期
	 *
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/**
	 * 账户是否锁定
	 *
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/**
	 * 用户凭证是否过期
	 *
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/**
	 * 用户是否激活
	 *
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
