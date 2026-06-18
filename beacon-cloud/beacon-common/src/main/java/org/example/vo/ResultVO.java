package org.example.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private T data;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private long total;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Object rows;
	
	public ResultVO() {
	}
	
	public ResultVO(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
