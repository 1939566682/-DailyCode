package org.example.util;

import org.example.enums.ExceptionEnums;
import org.example.vo.ResultVO;

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
	 * @return
	 */
	public static ResultVO ok() {
		return new ResultVO(0,"success");
	}
	
	/**
	 * 失败 指定错误信息
	 * @param enums
	 * @return
	 */
	public static ResultVO error(ExceptionEnums enums){
		return new ResultVO(enums.getCode(),enums.getMessage());
	}
	
}
