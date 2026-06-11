package org.example.util;

import org.example.enums.CMPP2ResultEnums;

import java.util.HashMap;

/**
 * CMPP2ResultUtil
 *
 * @author Yang QingBo
 * @date 2026-06-10 21:41
 * @description
 */

public class CMPP2ResultRespUtil {
	private static HashMap<Integer, String> operators = new HashMap();
	
	static {
		CMPP2ResultEnums[] cmpp2ResultEnums = CMPP2ResultEnums.values();
		for (CMPP2ResultEnums cmpp2ResultEnum : cmpp2ResultEnums) {
			operators.put(cmpp2ResultEnum.getResult(), cmpp2ResultEnum.getMessage());
		}
	}
	
	/**
	 * 通过result结果拿到对应的错误信息
	 *
	 * @param result
	 * @return
	 */
	public static String getResultMessage(Integer result) {
		return operators.get(result);
	}
}
