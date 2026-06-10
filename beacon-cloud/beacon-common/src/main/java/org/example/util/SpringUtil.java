package org.example.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpingUtil
 *
 * @author Yang QingBo
 * @date 2026-06-02 22:39
 * @description
 */

@Component
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}
	
	public static <T> T getBean(String beanName) {
		return (T) SpringUtil.applicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> beanClass) {
		return SpringUtil.applicationContext.getBean(beanClass);
	}
}
