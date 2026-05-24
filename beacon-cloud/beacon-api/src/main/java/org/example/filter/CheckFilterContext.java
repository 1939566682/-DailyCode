package org.example.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * CheckFilterContext
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:51
 * @description 策略模式的上下文对象
 */

@Component
@RefreshScope
public class CheckFilterContext {

	// Spring的IOC会将对象全部都放到Map集合中
	// 基于4.x中spring提供的泛型注入  基于Map只拿到我们需要的类型对象
	@Autowired
	private Map<String, CheckFilter> checkFiltersMap;
	
	@Value("${filters:apikey,ip,sign,template,mobile,fee}")
	private String filters;
	
	/**
	 * 当前check方法用于管理校验链的顺序
	 * @param obj
	 */
	public void check(Object obj){
		// 将获取到的filters基于,切分
		String[] filterArray = filters.split(",");
		// 遍历数组
		for (String filter : filterArray) {
			CheckFilter checkFilter = checkFiltersMap.get(filter);
			if (checkFilter != null) {
				checkFilter.check(obj);
			}
		}
	}

}
