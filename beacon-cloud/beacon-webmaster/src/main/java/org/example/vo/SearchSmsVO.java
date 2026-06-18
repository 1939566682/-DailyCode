package org.example.vo;

import lombok.Data;

/**
 * SearchSmsVO
 *
 * @author Yang QingBo
 * @date 2026-06-18 16:54
 * @description
 */

@Data
public class SearchSmsVO {

	private String corpname;
	
	private String sendTimeStr;
	
	private Integer reportState;
	
	private Integer operatorId;
	
	private String errorMsg;
	
	private String srcNumber;
	
	private String mobile;
	
	private String text;
	
	
}
