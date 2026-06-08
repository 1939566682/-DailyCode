package org.example.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
 * @description 状态报告推送等操作时的类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardReport implements Serializable {
	
	private static final long serialVersionUID = -1607838843843963557L;
	
	/**
	 * 针对当前短信的唯一标识
	 */
	private Long sequenceId;
	
	/**
	 * 客户端ID
	 */
	private Long clientId;
	
	/**
	 * 客户业务内的uid
	 */
	private String uid;
	
	/**
	 * 目标手机号
	 */
	private String mobile;
	
	/**
	 * 短信的发送时间
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime sendTime;
	
	
	/**
	 * 短信的发送状态， 0-等待ing，1-成功，2-失败
	 */
	private int reportState;
	
	/**
	 * 短信发送失败的原因
	 */
	private String errorMsg;
	
	/**
	 * 状态报告是否返回：0 不返回  1 返回
	 */
	private Integer isCallback;
	
	/**
	 * 客户接受状态报告的URL地址
	 */
	private String callbackUrl;
}
