package org.example.filter;

import org.example.model.StandardSubmit;

/**
 * StrategyFilter
 *
 * @author Yang QingBo
 * @date 2026-06-01 16:22
 * @description
 */

public interface StrategyFilter {
	
	/**
	 * 校验
	 * @param submit
	 */
	void strategy(StandardSubmit submit);

}
