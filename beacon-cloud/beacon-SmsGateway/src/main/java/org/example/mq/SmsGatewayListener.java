package org.example.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.model.StandardSubmit;
import org.example.netty4.NettyClient;
import org.example.netty4.entity.CmppSubmit;
import org.example.netty4.utils.Command;
import org.example.netty4.utils.MsgUtils;
import org.example.util.CMPPSubmitRespMapUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * SmsGatewayListener
 *
 * @author Yang QingBo
 * @date 2026-06-09 20:08
 * @description
 */

@Slf4j
@Component
public class SmsGatewayListener {
	
	@Autowired
	private NettyClient nettyClient;

	@RabbitListener(queues = "${gateway.sendtopic}")
	public void consume(StandardSubmit submit, Channel channel, Message message) throws IOException {
		log.info("【短信网关模块】  接收到消息 submit = {}",submit);
		// ================完成与运营商交互  发送一次请求  接受两次响应================
		// 1、获取需要的核心属性
		String srcId = submit.getSrcNumber();
		// 这个序列是基于++实现的  当取值达到max时  会被重置  这个值是可以重复利用的
		int sequenceId = MsgUtils.getSequence();
		String mobile = submit.getMobile();
		String content = submit.getText();
		// 2、声明发送短信时需要的CMPPSubmit对象
		CmppSubmit cmppSubmit = new CmppSubmit(Command.CMPP2_VERSION,srcId,sequenceId,mobile,content);
		// 3、将submit做一个临时存储  在运营商第一次响应时  可以获取到
		// TODO 后续可增加健壮性处理  将数据存放到redis中  避免服务器宕机丢失数据
		CMPPSubmitRespMapUtil.put(sequenceId,submit);
		// 4、和运营商交互 发送短信
		nettyClient.submit(cmppSubmit);
		
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}

}
