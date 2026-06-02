package org.example.util;

import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * DFAUtil
 *
 * @author Yang QingBo
 * @date 2026-06-02 19:38
 * @description
 */

public class DFAUtil {
	// 敏感词树
	private static Map dfaMap = new HashMap();
	
	private static final String IS_END = "isEnd";
	
	private static final String NOT_END = "0";
	private static final String ALREADY_END = "1";
	
	/**
	 * 初始化敏感词树
	 */
	static {
		// 获取Spring容器中的cacheClient
		BeaconCacheClient cacheClient = SpingUtil.getBean(BeaconCacheClient.class);
		// 获取存储在redis中的全部敏感词
		Set<String> dirtyWords = cacheClient.sMember(CacheConstant.DIRTY_WORD);
		// 调用create  将dfa的敏感词树构建
		create(dirtyWords);
	}
	
	
	/**
	 * 构建敏感词树
	 * @param dirtyWords
	 */
	public static void create(Set<String> dirtyWords) {
		// 1、声明一个HashMap作为临时存储
		Map nowMap;
		
		// 2、遍历敏感词库
		for (String dirtyWord : dirtyWords) {
			nowMap = dfaMap;
			// 每个词  依次获取
			for (int i = 0; i < dirtyWord.length(); i++) {
				// 获取敏感词的每个字
				String word = String.valueOf(dirtyWord.charAt(i));
				// 判断当前敏感词树中是否包含当前字
				Map map = (Map) nowMap.get(word);
				if (map == null) {
					// 当前敏感词树没有这个字
					map = new HashMap();
					// 将当前的敏感词出入敏感词树
					nowMap.put(word, map);
				}
				// 操作当前key的value的map
				nowMap = map;
				// 如果当前字已经有 isEnd 且 isEnd = 1  则直接跳过
				if (nowMap.containsKey(IS_END) && nowMap.get(IS_END) == ALREADY_END) {
					continue;
				}
				// 此时 isEnd 不存在 或 isEnd = 0
				if (i == dirtyWord.length() - 1) {
					// 若此时当前字是最后一个字  则直接将isEnd设置为1
					nowMap.put(IS_END, ALREADY_END);
				} else {
					// 若也不是最后一个字 直接将isEnd设置为0
					nowMap.put(IS_END, NOT_END);
				}
			}
		}
	}
	
	/**
	 * 基于敏感词树  对文字进行敏感词获取
	 * @param text
	 * @return 敏感词Set集合
	 */
	public static Set<String> getDirtyWords(String text) {
		// 1、作为返回结果存储敏感词的位置
		Set<String> dirtyWords = new HashSet<>();
		// 2、循环遍历文本内容
		for (int i = 0; i < text.length(); i++) {
			// 临时存储索引位置的变量
			int nextLength = 0;
			int dirtyLength = 0;
			// 获取最外层key的map
			Map nowMap = dfaMap;
			// 外层是索引向后动  匹配最外层的key
			// 内层是匹配上一个后  继续向内部匹配内部的key
			for (int j = i; j < text.length(); j++) {
				// 获取当前索引位置的字
				String word = String.valueOf(text.charAt(j));
				// 先匹配外层的字
				nowMap = (Map) nowMap.get(word); // 拿到内层map
				// 判断
				if (nowMap == null) {
					// 没有这个字开头的敏感词
					break;
				} else {
					// 敏感词长度从i开始算  现在的是dirtyLength
					dirtyLength++;
					// 出口：若当前map的isEnd是1  代表结束了  找到了完整的敏感词
					if (ALREADY_END.equals(nowMap.get(IS_END))) {
						// 代表敏感词匹配到一个完整的
						nextLength = dirtyLength;
						break;
					}
				}
			}
			// 判断是否匹配上了敏感词
			if (nextLength > 0) {
				// 匹配上了  添加敏感词到set  同时移动外层索引
				dirtyWords.add(text.substring(i, i + nextLength));
				i = i + nextLength - 1; // -1的原因：外层for会对i进行++，-1避免两个敏感词相连是无法检测到第二个敏感词
			}
		}
		return dirtyWords;
	}
	
	
}
