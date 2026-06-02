package org.example.util;

import cn.hutool.dfa.WordTree;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;

import java.util.List;
import java.util.Set;

/**
 * HutoolDFAUtil
 *
 * @author Yang QingBo
 * @date 2026-06-02 23:12
 * @description
 */

public class HutoolDFAUtil {
	
	private static WordTree wordTree = new WordTree();
	
	/**
	 * 初始化敏感词树
	 */
	static {
		// 获取Spring容器中的cacheClient
		BeaconCacheClient cacheClient = SpingUtil.getBean(BeaconCacheClient.class);
		// 获取存储在Redis中的全部敏感词
		Set<String> dirtyWords = cacheClient.sMember(CacheConstant.DIRTY_WORD);
		// 调用WordTree的add方法，将dfaMap的敏感词树构建
		wordTree.addWords(dirtyWords);
	}
	
	
	public static List<String> getDirtyWord(String text){
		return wordTree.matchAll(text);
	}
	
}
