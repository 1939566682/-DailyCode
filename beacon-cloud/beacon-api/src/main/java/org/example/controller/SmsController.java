package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.enums.SmsCodeEnum;
import org.example.form.SingleSendForm;
import org.example.util.R;
import org.example.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SmsController {

	
	@PostMapping(value = "/single_send",produces = "application/json;charset=utf-8")
	public ResultVO singleSend(@RequestBody @Validated SingleSendForm singleSendForm, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
			log.error("【接口模块 - 单条短信Controller】 参数不合法 msg：{}",defaultMessage);
			return R.error(SmsCodeEnum.PARAMETER_ERROR.getCode(),defaultMessage);
		}
		// ======================构建StandardSubmit  然后封装校验==============================
		
		
		// ======================发送到MQ  交给策略模块处理==============================
		
		return R.ok();
	}

}
