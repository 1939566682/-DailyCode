package org.example.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.example.realm.ShiroRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig
 *
 * @author Yang QingBo
 * @date 2026-06-15 11:33
 * @description
 */

@Configuration
public class ShiroConfig {
	
	@Bean
	public DefaultWebSecurityManager securityManager(ShiroRealm realm) {
		// 1、构建
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		// 2、设置Realm进去
		securityManager.setRealm(realm);
		
		// 3、返回安全管理器
		return securityManager;
	}
	
	/**
	 * 设置过滤器链的规则
	 * @return
	 */
	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		// 1、构建实现类
		DefaultShiroFilterChainDefinition shiroFilter = new DefaultShiroFilterChainDefinition();
		
		// 2、配置过滤器链
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		/**
		 * anon 代表放行  使用的是 AnonymousFilter
		 * logout 代表登出  使用的是 LogoutFilter
		 */
		filterChainDefinitionMap.put("/public/**","anon");
		filterChainDefinitionMap.put("/captcha.jpg","anon");
		filterChainDefinitionMap.put("/sys/user/login","anon");
		filterChainDefinitionMap.put("/index.html","anon");
		filterChainDefinitionMap.put("/login.html","anon");
		filterChainDefinitionMap.put("/logout","logout");
		filterChainDefinitionMap.put("/**","authc");
		shiroFilter.addPathDefinitions(filterChainDefinitionMap);
		
		// 3、返回配置好的过滤器链
		return shiroFilter;
	}

}
