package org.example.vo;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ResultVO
 *
 * @author Yang QingBo
 * @date 2026-05-24 16:33
 * @description
 */

@Data
public class ResultVO {
	
	/**
	 * 0代表接收成功，其他code代表出错
	 */
	private  Integer code;
	
	private String msg;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer count;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long fee;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String uid;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long sid;

}
