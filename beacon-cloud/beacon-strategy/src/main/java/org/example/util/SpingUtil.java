package org.example.util;

import javafx.application.Application;
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
public class SpingUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpingUtil.applicationContext = applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return SpingUtil.applicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> beanClass) {
		return SpingUtil.applicationContext.getBean(beanClass);
	}
}
