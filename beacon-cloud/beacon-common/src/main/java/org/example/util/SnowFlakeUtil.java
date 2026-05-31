package org.example.util;

/**
 * SnowFlakeUtil
 * 雪花算法生成全局唯一的ID
 * 64个bit位的long类型的值
 * 第一位：占 1 个bit位 就是0
 * 第二位：占 41 个bit位  代表时间戳
 * 第三位：占 5 个bit位  代表机器ID
 * 第四位：占 5 个bit位  代表服务ID
 * 第五位：占 12 个bit位  代表序列  自增的数值
 *
 * @author Yang QingBo
 * @date 2026-05-31 21:34
 * @description
 */

public class SnowFlakeUtil {
	
	/**
	 * 41个bit位存储时间戳  从 0 开始计算  最多可以存储69.7年
	 */
	
}
