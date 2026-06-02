package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.example.util.HutoolDFAUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DirtyWordHutoolDFAStrategyFilter
 *
 * @author Yang QingBo
 * @date 2026-06-02 23:14
 * @description
 */

@Service(value = "hutoolDFADirtyWord")
@Slf4j
public class DirtyWordHutoolDFAStrategyFilter implements StrategyFilter {
	
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块-敏感词校验】   校验ing…………");
		//1、 获取短信内容
		String text = submit.getText();
		
		//2、 调用DFA查看敏感词
		List<String> dirtyWords = HutoolDFAUtil.getDirtyWord(text);
		
		//4、 根据返回的set集合，判断是否包含敏感词
		if (dirtyWords != null && dirtyWords.size() > 0) {
			//5、 如果有敏感词，抛出异常 / 其他操作。。
			log.info("【策略模块-敏感词校验】   短信内容包含敏感词信息， dirtyWords = {}", dirtyWords);
			// 还需要做其他处理
		}
	}
}
