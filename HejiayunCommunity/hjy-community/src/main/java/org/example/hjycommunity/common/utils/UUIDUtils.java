package org.example.hjycommunity.common.utils;

import java.util.UUID;

/**
 * UUIDUtils
 * UUID生成器工具类
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 18:05
 */

public class UUIDUtils {
	/**
	 * 获取随机UUID
	 *
	 * @return 随机UUID
	 */
	public static String randomUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 简化的UUID，去掉了横线
	 *
	 * @return 简化的UUID，去掉了横线
	 */
	public static String simpleUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
