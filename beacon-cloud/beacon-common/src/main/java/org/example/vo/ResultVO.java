package org.example.vo;

import lombok.Data;

/**
 * ResultVO
 *
 * @author Yang QingBo
 * @date 2026-06-15 17:53
 * @description 响应前端数据的基本结构
 */

@Data
public class ResultVO<T> {
	
	private Integer code;
	
	private String msg;
	
	private T data;
	
	public ResultVO() {
	}
	
	public ResultVO(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResultVO(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}
