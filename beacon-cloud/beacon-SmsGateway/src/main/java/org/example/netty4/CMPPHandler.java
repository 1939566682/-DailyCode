package org.example.netty4;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.SmsConstant;
import org.example.enums.CMPP2ResultEnums;
import org.example.model.StandardReport;
import org.example.model.StandardSubmit;
import org.example.netty4.entity.CmppDeliver;
import org.example.netty4.entity.CmppSubmitResp;
import org.example.netty4.utils.MsgUtils;
import org.example.runnable.SubmitRespRunnable;
import org.example.util.CMPP2ResultUtil;
import org.example.util.CMPPSubmitRespMapUtil;
import org.example.util.SpringUtil;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * 主要业务 handler,运营商响应信息
 */
@Slf4j
public class CMPPHandler extends SimpleChannelInboundHandler {
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext context, Object msg) throws Exception {
		
		if (msg instanceof CmppSubmitResp) {
			CmppSubmitResp resp = (CmppSubmitResp) msg;
			log.info("-------------接收到短信提交应答-------------");
			log.info("----自增id：{}", resp.getSequenceId());
			log.info("----状态：{}", resp.getResult());
			log.info("----第一次响应：{}", resp.getMsgId());
			
			// 4、将封装好的任务放到线程池中执行即可
			ThreadPoolExecutor cmppSubmitPool = SpringUtil.getBean("cmppSubmitPool");
			cmppSubmitPool.execute(new SubmitRespRunnable(resp));
		}
		
		if (msg instanceof CmppDeliver) {
			CmppDeliver resp = (CmppDeliver) msg;
			// 是否为状态报告 0：非状态报告1：状态报告
			if (resp.getRegistered_Delivery() == 1) {
				// 如果是状态报告的话
				log.info("-------------状态报告---------------");
				log.info("----第二次响应：{}", resp.getMsg_Id_DELIVRD());
				log.info("----手机号：{}", resp.getDest_terminal_Id());
				log.info("----状态：{}", resp.getStat());
				// 先放这里避免报错  也是提前获取
				ThreadPoolExecutor cmppDeliverPool = SpringUtil.getBean("cmppDeliverPool");
			} else {
				//用户回复会打印在这里
				log.info("{}", MsgUtils.bytesToLong(resp.getMsg_Id()));
				log.info(resp.getSrc_terminal_Id());
				log.info(resp.getMsg_Content());
			}
		}
	}
	
}
