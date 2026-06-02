package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
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
@Service(value = "dirtyword")
public class DirtyWordStrategyFilter implements StrategyFilter {
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 敏感词校验】  校验ing......");
		// 1、获取短信内容
		String text = submit.getText();
		
		// 2、使用ik分词器对短信内容进行分词  并将分词内容存储到集合中
		Set<String> contents = new HashSet<String>();
		IKSegmenter ik = new IKSegmenter(new StringReader(text), true);
		Lexeme lex = null;
		while (true) {
			try {
				if ((lex = ik.next()) == null) break;
			} catch (IOException e) {
				log.info("【策略模块 - 敏感词校验】 IK分词器在处理短信内容时 出现异常 e = {}", e.getMessage());
			}
			contents.add(lex.getLexemeText());
		}
		
		// 3、调用Cache缓存模块的交集方法  拿到结果
		Set<Object> dirtyWords = beaconCacheClient.sinterStr(UUID.randomUUID().toString(), CacheConstant.DIRTY_WORD, contents.toArray(new String[]{}));
		
		// 4、根据返回的Set集合  判断是否包含敏感词
		if (dirtyWords != null && !dirtyWords.isEmpty()) {
			// 5、如果有敏感词  抛出异常 / 其他操作
			log.info("【策略模块 - 敏感词校验】 短信内容包含敏感词信息 dirtyWords = {}", dirtyWords);
			// TODO 还需其他处理
		}
		
		
	}
}
