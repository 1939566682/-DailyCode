package org.example.util;

import org.example.model.StandardReport;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CMPPDeliverMapUtil
 *
 * @author Yang QingBo
 * @date 2026-06-10 22:02
 * @description 用于CMPP的状态回调时  获取核心信息的方式
 */

public class CMPPDeliverMapUtil {
	
	private static ConcurrentHashMap<String, StandardReport> map = new ConcurrentHashMap<>();
	
	public static void put(String msgId, StandardReport standardReport) {
		map.put(msgId, standardReport);
	}
	
	public static StandardReport get(String msgId) {
		return map.get(msgId);
	}
	
	public static StandardReport remove(String msgId) {
		return map.remove(msgId);
	}
	
}
