package org.example.util;

import java.util.regex.Pattern;

/**
 * PhoneFormatCheckUtil
 * 校验手机号格式的合法性正则：^(13[0-9]|14[5-7]|15[0-35-9]|166|17[0-8]|18[0-9]|19[89])\d{8}$
 * @author Yang QingBo
 * @date 2026-05-31 18:02
 * @description
 */

public class PhoneFormatCheckUtil {
	
	/**
	 * 国内手机号的正则表达式
	 */
	private static final Pattern CHINA_PATTERN = Pattern.compile("^(13[0-9]|14[5-7]|15[0-35-9]|166|17[0-8]|18[0-9]|19[89])\\d{8}$");
	
	public static boolean isChinaPhone(String phone) {
		return CHINA_PATTERN.matcher(phone).matches();
	}

}
