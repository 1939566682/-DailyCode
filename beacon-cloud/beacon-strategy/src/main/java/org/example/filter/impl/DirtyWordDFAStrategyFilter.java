package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.example.util.DFAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * PhaseStrategyFilter
 * 敏感词校验
 *
 * @author Yang QingBo
 * @date 2026-06-01 16:23
 * @description
 */

@Slf4j
@Service(value = "dfaDirtyWord")
public class DirtyWordDFAStrategyFilter implements StrategyFilter {
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 敏感词校验】  校验ing......");
		// 1、获取短信内容
		String text = submit.getText();
		
		// 2、调用DFA查看敏感词
		Set<String> dirtyWords = DFAUtil.getDirtyWords(text);
		
		// 4、根据返回的Set集合  判断是否包含敏感词
		if (dirtyWords != null && !dirtyWords.isEmpty()) {
			// 5、如果有敏感词  抛出异常 / 其他操作
			log.info("【策略模块 - 敏感词校验】 短信内容包含敏感词信息 dirtyWords = {}", dirtyWords);
			// TODO 还需其他处理
		}
		
		
	}
}
