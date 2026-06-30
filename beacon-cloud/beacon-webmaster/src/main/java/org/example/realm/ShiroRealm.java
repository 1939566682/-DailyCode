package org.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.example.entity.SmsUser;
import org.example.service.SmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ShiroRealm
 *
 * @author Yang QingBo
 * @date 2026-06-15 11:56
 * @description 自定义Realm
 */

@Component
public class ShiroRealm extends AuthorizingRealm {
	
	{
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1024);
		this.setCredentialsMatcher(hashedCredentialsMatcher);
	}
	
	@Autowired
	private SmsUserService smsUserService;
	
	/**
	 * 认证
	 *
	 * @param token the authentication token containing the user's principal and credentials.
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1、基于token拿到用户名
		String username = token.getPrincipal().toString();
		
		// 2、基于用户名获取用户信息
		SmsUser smsUser = smsUserService.findByUsername(username);

		// 3、查询完毕后 查看用户是否为null 为null就直接返回即可
		if (smsUser == null) {
			// 用户名错误
			return null;
		}
		
		// 4、不为null 说明用户名正确 封装AuthenticationInfo 设置密码的加密方式和信息
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(smsUser, smsUser.getPassword(), "ShiroRealm");
		info.setCredentialsSalt(ByteSource.Util.bytes(smsUser.getSalt()));
		
		// 5、返回
		return info;
	}
	
	/**
	 * 授权
	 *
	 * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
}
