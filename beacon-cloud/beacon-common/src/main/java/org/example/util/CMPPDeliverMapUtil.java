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
	
	public static void put(int sequenceId, StandardReport standardReport) {
		map.put(sequenceId + "", standardReport);
	}
	
	public static StandardReport get(int sequenceId) {
		return map.get(sequenceId);
	}
	
	public static StandardReport remove(int sequenceId) {
		return map.remove(sequenceId);
	}
	
}
