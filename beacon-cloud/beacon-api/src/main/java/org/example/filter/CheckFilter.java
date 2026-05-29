package org.example.filter;

import org.example.model.StandardSubmit;

/**
 * CheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:28
 * @description 做策略模式的父接口
 */

public interface CheckFilter {
	
	/**
	 * 校验
	 * @param submit
	 */
	void check(StandardSubmit submit);
	
}
