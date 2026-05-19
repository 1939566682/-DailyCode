package org.example.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GlobeCache
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 19:43
 */
public class GlobeCache {
	
	private static final Map<String, Object> map = new ConcurrentHashMap<>();
	
	private GlobeCache() {
		// 私有构造器，防止实例化
	}
	
	/**
	 * 设置缓存
	 *
	 * @param key   键
	 * @param value 值
	 */
	public static void set(String key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		map.put(key, value);
	}
	
	/**
	 * 获取缓存（类型安全）
	 *
	 * @param key 键
	 * @param <T> 期望的值类型
	 * @return 缓存值，可能为 null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		if (key == null) {
			return null;
		}
		return (T) map.get(key);
	}
	
	/**
	 * 移除指定 key 的缓存
	 *
	 * @param key 键
	 */
	public static void remove(String key) {
		if (key != null) {
			map.remove(key);
		}
	}
	
	/**
	 * 清空所有缓存
	 */
	public static void clear() {
		map.clear();
	}
	
	/**
	 * 检查是否包含某个 key
	 *
	 * @param key 键
	 * @return true 如果存在
	 */
	public static boolean containsKey(String key) {
		return key != null && map.containsKey(key);
	}
	
	/**
	 * 获取缓存大小
	 *
	 * @return 缓存条目数
	 */
	public static int size() {
		return map.size();
	}
}
