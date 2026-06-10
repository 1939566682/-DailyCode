package org.example.util;

import org.example.model.StandardSubmit;

import java.util.concurrent.ConcurrentHashMap;

/**
 * CMPPSubmitRespMapUtil
 *
 * @author Yang QingBo
 * @date 2026-06-10 17:24
 * @description 用于CMPP发送短信时  临时存储的位置
 */

public class CMPPSubmitRespMapUtil {
	
	private static ConcurrentHashMap<String, StandardSubmit> map = new ConcurrentHashMap<>();
	
	public static void put(int sequenceId, StandardSubmit standardSubmit) {
		map.put(sequenceId + "", standardSubmit);
	}
	
	public static StandardSubmit get(int sequenceId) {
		return map.get(sequenceId);
	}
	
	public static StandardSubmit remove(int sequenceId) {
		return map.remove(sequenceId);
	}
	
}
