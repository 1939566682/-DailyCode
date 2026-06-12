package org.example;

/**
 * SearchEnums
 *
 * @author Yang QingBo
 * @date 2026-06-12 15:36
 * @description
 */

public enum SearchEnums {
	
	INDEX("sms_submit_log_");
	
	private final String index;
	
	SearchEnums(String index) {
		this.index = index;
	}
	
	public String getIndex() {
		return index;
	}
}
