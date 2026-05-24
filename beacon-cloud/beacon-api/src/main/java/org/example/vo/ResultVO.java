package org.example.vo;

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
	private Integer count;
	private Long fee;
	private String uid;
	private Long sid;

}
