package org.example.utils;

import java.time.LocalDate;

/**
 * SearchUtils
 *
 * @author Yang QingBo
 * @date 2026-06-12 15:39
 * @description
 */

public class SearchUtils {
	
	public static String getYear(){
		return String.valueOf(LocalDate.now().getYear());
	}
}
