package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.constant.RabbitMQConstant;
import org.example.enums.ExceptionEnums;
import org.example.filter.CheckFilterContext;
import org.example.form.SingleSendForm;
import org.example.model.StandardSubmit;
import org.example.util.R;
import org.example.util.SnowFlakeUtil;
import org.example.vo.ResultVO;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * SmsController
 *
 * @author Yang QingBo
 * @date 2026-05-24 16:25
 * @description
 */

@Slf4j
@RestController
@RequestMapping("sms")
@RefreshScope
public class SmsController {
	
	@Autowired
	private SnowFlakeUtil snowFlakeUtil;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 客户端IP地址的请求头信息  多个用","隔开
	 */
	@Value("${headers:x-forwarded-for,x-real-ip,Proxy-Client-IP,WL-Proxy-Client-IP,HTTP_CLIENT_IP,HTTP_X_FORWARDED_FOR}")
	private String headers;
	
	/**
	 * 基于请求头获取信息时可能获取到的未知信息
	 */
	private final String UNKNOW = "unknown";
	
	/**
	 * 如果是当前请求头获取IP地址  需要截取到第一个有效ip
	 */
	private final String X_FORWARDED_FOR = "x-forwarded-for";
	
	@Autowired
	private CheckFilterContext checkFilterContext;
	
	/**
	 * 单条短信验证接口
	 *
	 * @param singleSendForm
	 * @param bindingResult
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/single_send", produces = "application/json;charset=utf-8")
	public ResultVO singleSend(@RequestBody @Validated SingleSendForm singleSendForm, BindingResult bindingResult, HttpServletRequest req) {
		if (bindingResult.hasErrors()) {
			String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
			log.error("【接口模块 - 单条短信Controller】 参数不合法 msg：{}", defaultMessage);
			return R.error(ExceptionEnums.PARAMETER_ERROR.getCode(), defaultMessage);
			
			
		}
		// ======================获取真实的IP地址==============================
		String realIP = this.getRealIP(req);
		
		// ======================构建StandardSubmit  然后封装校验==============================
		StandardSubmit submit = new StandardSubmit();
		submit.setRealIP(realIP);
		submit.setApiKey(singleSendForm.getApikey());
		submit.setMobile(singleSendForm.getMobile());
		submit.setText(singleSendForm.getText());
		submit.setUid(singleSendForm.getUid());
		submit.setState(singleSendForm.getState());
		
		// ======================调用策略模式的校验链==============================
		checkFilterContext.check(submit);
		
		// ======================基于雪花算法生成唯一id 并添加到StandardSubmit对象中  并设置发送时间==============================
		submit.setSequenceId(snowFlakeUtil.nextId());
		submit.setSendTime(LocalDateTime.now());
		
		// ======================发送到MQ  交给策略模块处理==============================
		rabbitTemplate.convertAndSend(RabbitMQConstant.SMS_PRE_SEND, submit,new CorrelationData(submit.getSequenceId().toString()));
		
		return R.ok();
	}
	
	/**
	 * 获取客户端真实的IP地址
	 *
	 * @param request
	 * @return
	 */
	private String getRealIP(HttpServletRequest request) {
		// 从配置中解析请求头名称，多个以逗号分隔
		String[] headerNames = headers.split(",");
		for (String name : headerNames) {
			String headerName = name.trim();
			if (headerName.isEmpty()) {
				continue;
			}
			String value = request.getHeader(headerName);
			if (value == null || value.isEmpty() || UNKNOW.equalsIgnoreCase(value)) {
				continue;
			}
			// X-Forwarded-For 可能包含多个 IP，取第一个有效的
			if (X_FORWARDED_FOR.equalsIgnoreCase(headerName)) {
				for (String part : value.split(",")) {
					String ip = part.trim();
					if (!ip.isEmpty() && !UNKNOW.equalsIgnoreCase(ip)) {
						return ip;
					}
				}
			} else {
				return value.trim();
			}
		}
		// 回退到直接连接的 IP
		String remoteAddr = request.getRemoteAddr();
		return (remoteAddr != null && !remoteAddr.isEmpty() && !UNKNOW.equalsIgnoreCase(remoteAddr)) ? remoteAddr : UNKNOW;
	}
	
}
