package org.example.util;

/**
 * ClientBalanceUtil
 *
 * @author Yang QingBo
 * @date 2026-06-05 14:49
 * @description
 */

public class ClientBalanceUtil {
	
	/**
	 * 后期若要给客户指定欠费的额度登记  再重写方法
	 *
	 * @param clientId
	 * @return
	 */
	
	public static Long getClientAmountLimit(Long clientId) {
		return -10000L;
	}
	
}
