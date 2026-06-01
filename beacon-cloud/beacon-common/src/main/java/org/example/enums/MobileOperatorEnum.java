package org.example.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * MobileOperatorEnum
 *
 * @author Yang QingBo
 * @date 2026-06-01 20:38
 * @description
 */

@Getter
public enum MobileOperatorEnum {
	
	
	CHINA_MOBILE(1,"移动"),
	CHINA_UNICOM(2,"联通"),
	CHINA_TELECOM(3,"电信");
	
	private Integer operatorId;
	
	private String operatorName;
	
	
	MobileOperatorEnum(Integer operatorId, String operatorName) {
		this.operatorId = operatorId;
		this.operatorName = operatorName;
	}
}
