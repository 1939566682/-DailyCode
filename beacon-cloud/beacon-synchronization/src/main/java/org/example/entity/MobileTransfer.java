package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MobileTransfer
 *
 * @author Yang QingBo
 * @date 2026-06-03 21:20
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileTransfer {
	
	/**
	 * 手机号
	 */
	private String transferNumber;
	
	/**
	 * 最终运营商
	 */
	private Integer nowIsp;
}
