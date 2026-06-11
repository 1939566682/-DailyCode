package org.example.util;

import org.example.enums.CMPP2DeliverEnums;

import java.util.HashMap;

/**
 * CMPP2DeliverUtil
 *
 * @author Yang QingBo
 * @date 2026-06-11 15:30
 * @description
 */

public class CMPP2DeliverRespUtil {
	private static HashMap<String, String> operators = new HashMap<>();
	
	static {
		CMPP2DeliverEnums[] cmpp2ResultEnums = CMPP2DeliverEnums.values();
		for (CMPP2DeliverEnums cmpp2DeliverEnum : cmpp2ResultEnums) {
			operators.put(cmpp2DeliverEnum.getStat(), cmpp2DeliverEnum.getDescription());
		}
	}
	
	/**
	 * 通过result结果拿到对应的错误信息
	 *
	 * @param stat
	 * @return
	 */
	public static String getResultMessage(String stat) {
		return operators.get(stat);
	}
}
