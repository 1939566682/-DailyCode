package org.example.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.example.SearchEnums;
import org.example.constant.RabbitMQConstant;
import org.example.model.StandardSubmit;
import org.example.service.SearchService;
import org.example.util.JsonUtil;
import org.example.utils.SearchUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

/**
 * SmsWriteLogListener
 *
 * @author Yang QingBo
 * @date 2026-06-08 14:27
 * @description
 */

@Slf4j
@Component
public class SmsWriteLogListener {
	
	
	
	@Autowired
	private SearchService searchService;
	
	
	@RabbitListener(queues = RabbitMQConstant.SMS_WRITE_LOG)
	public void consume(StandardSubmit submit, Channel channel, Message message) throws IOException {
		// 1、调用搜索模块的搜索方法  完成添加操作
		log.info("【搜索模块】  接收到存储日志的信息 submit = {}",submit);
		searchService.index(SearchUtils.getCurrentYearIndex(),submit.getSequenceId().toString(), JsonUtil.ObjectToJson(submit));
		
		// 2、手动ack
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}
	
}
