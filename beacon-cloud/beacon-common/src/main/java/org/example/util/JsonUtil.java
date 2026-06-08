package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtil
 *
 * @author Yang QingBo
 * @date 2026-06-08 15:38
 * @description
 */

public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static String ObjectToJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("转换json对象失败！");
		}
	}
	
}
