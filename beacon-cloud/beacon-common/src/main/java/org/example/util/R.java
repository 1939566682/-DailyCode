package org.example.util;

import org.example.enums.ExceptionEnums;
import org.example.vo.ResultVO;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * R
 *
 * @author Yang QingBo
 * @date 2026-06-15 17:54
 * @description 封装ResultVO的工具
 */

public class R {
	
	/**
	 * 成功 无数据
	 */
	public static<T> ResultVO<T> ok() {
		return new ResultVO<>(0,"success");
	}
	
	
	/**
	 * 成功 有数据
	 */
	public static<T> ResultVO<T> ok(T data) {
		ResultVO<T> vo = ok();
		vo.setData(data);
		return vo;
	}
	
	/**
	 * 成功 有数据
	 */
	public static<T> ResultVO<T> ok(Long total,Object rows) {
		ResultVO<T> vo = ok();
		vo.setTotal(total);
		vo.setRows(rows);
		return vo;
	}
	
	/**
	 * 成功 指定字段名返回数据（前端JS期望特定命名的字段，如 r.acount、r.sites 等）
	 * 返回 Map 格式: {code: 0, msg: "success", <key>: <value>}
	 */
	public static Map<String, Object> okNamed(String key, Object value) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("code", 0);
		map.put("msg", "success");
		map.put(key, value);
		return map;
	}
	
	/**
	 * 失败 指定错误信息
	 */
	public static<T> ResultVO<T> error(ExceptionEnums enums){
		return new ResultVO<>(enums.getCode(),enums.getMessage());
	}
	
}
