package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * StandardSubmit
 *
 * @author Yang QingBo
 * @date 2026-05-24 17:27
 * @description 在接口模块 - 策略模块 - 短信网关模块 需要做校验和封装的POJO类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardSubmit implements Serializable {
	
	/**
	 * 针对当前短信的唯一标识
	 */
	private Long sequenceId;
	
	/**
	 * 客户端ID
	 */
	private Long clientId;
	
	/**
	 * 客户端的ip白名单
	 */
	private String ip;
	
	/**
	 * 客户业务内的uid
	 */
	private String uid;
	
	/**
	 * 目标手机号
	 */
	private String mobile;
	
	/**
	 * 短信内容的签名
	 */
	private String sign;
	
	/**
	 * 短信内容
	 */
	private String text;
	
	/**
	 * 短信的发送时间
	 */
	private LocalDateTime sendTime;
	
	/**
	 * 当前短信的费用  单位（厘）
	 */
	private Long fee;
	
	/**
	 * 目标手机号的运营商
	 */
	private Integer operatorId;
	
	
	/**
	 * 目标手机号的归属地区号  0451  0455
	 */
	private Integer areaCode;
	
	/**
	 * 目标手机号的归属地  哈尔滨，绥化
	 */
	private String area;
	
	/**
	 * 通道下发的源号码  106934985673485645
	 */
	private String srcNumber;
	
	/**
	 * 通道的id信息
	 */
	private Long channelId;
	
	/**
	 * 短信的发送状态， 0-等待ing，1-成功，2-失败
	 */
	private int reportState;
	
	// =================================================================
	
	/**
	 * 获取到的客户端真实IP地址
	 */
	private String realIP;
	
	/**
	 * 客户端请求携带的apiKey
	 */
	private String apiKey;
	
	/**
	 * 0-验证码短信 1-通知类短信 2-营销类短信
	 */
	private Integer state;
	
	/**
	 * 签名ID
	 */
	private Long signId;
}
