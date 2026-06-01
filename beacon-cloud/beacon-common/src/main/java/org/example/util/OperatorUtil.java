package org.example.util;

import org.example.enums.MobileOperatorEnum;

import java.util.HashMap;

/**
 * OperatorUtil
 *
 * @author Yang QingBo
 * @date 2026-06-01 20:43
 * @description
 */

public class OperatorUtil {
	
	private static HashMap<String, Integer> operators = new HashMap();
	
	static {
		MobileOperatorEnum[] enums = MobileOperatorEnum.values();
		for (MobileOperatorEnum operator : enums) {
			operators.put(operator.getOperatorName(), operator.getOperatorId());
		}
	}
	
	/**
	 * 通过运营商名称获取运营商id
	 * @param operatorName
	 * @return
	 */
	public static Integer getOperatorIdByOperatorName(String operatorName) {
		return operators.get(operatorName);
	}
}
