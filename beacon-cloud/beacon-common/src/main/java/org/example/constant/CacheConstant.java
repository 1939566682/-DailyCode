package org.example.constant;

/**
 * RedisCacheConstant
 *
 * @author Yang QingBo
 * @date 2026-05-29 14:43
 * @description 缓存模块中的各种前缀
 */

public interface CacheConstant {
	
	/**
	 * 客户信息
	 */
	String CLIENT_BUSINESS = "client_business:";
	
	/**
	 * 客户签名
	 */
	String CLIENT_SIGN = "client_sign:";
	
	/**
	 * 客户签名的模板
	 */
	String CLIENT_TEMPLATE = "client_template:";
	
	/**
	 * 客户的余额
	 */
	String CLIENT_BALANCE = "client_balance:";
	
	/**
	 * 手机号号段补全
	 */
	String PHASE = "phase:";
	
	/**
	 * 敏感词前缀
	 */
	String DIRTY_WORD = "dirty_word";
	
	/**
	 * 黑名单前缀
	 */
	String BLACK = "black:";
	
	/**
	 * 间隔符
	 */
	String SEPARATE = ":";
	
	/**
	 * 携号转网前缀
	 */
	String TRANSFER = "transfer:";
	
	/**
	 * 分钟的限流规则
	 */
	String LIMIT_MINUTES = "limit:minutes:";
	
	/**
	 * 小时的限流规则
	 */
	String LIMIT_HOURS = "limit:hours:";
}
