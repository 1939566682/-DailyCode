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
import org.example.util.CMPP2ResultUtil;
import org.example.util.CMPPSubmitRespMapUtil;
import org.springframework.beans.BeanUtils;


/**
 * 主要业务 handler,运营商响应信息
 */
@Slf4j
public class CMPPHandler extends SimpleChannelInboundHandler {
	
	private final Integer OK = 0;
	
	@Override
	protected void channelRead0(ChannelHandlerContext context, Object msg) throws Exception {
		
		if (msg instanceof CmppSubmitResp) {
			CmppSubmitResp resp = (CmppSubmitResp) msg;
			log.info("-------------接收到短信提交应答-------------");
			log.info("----自增id：{}", resp.getSequenceId());
			log.info("----状态：{}", resp.getResult());
			log.info("----第一次响应：{}", resp.getMsgId());
			// 1、拿到自增id 并且从ConcurrentHashMap中获取到存储的submit
			StandardSubmit submit = CMPPSubmitRespMapUtil.remove(resp.getSequenceId());
			
			// 2、根据运营商返回的submit  确认短信状态并封装submit
			int result = resp.getResult();
			StandardReport report = null;
//			if (result != OK) {
			// TODO 此处获取的result是运营商返回的submit的result  不应该用短信平台的消息状态码来进行判断  不影响结果但可能导致误解  （不确定  回头再看）
			if (result != CMPP2ResultEnums.OK.getResult()) {
				// 说明运营商的提交应答中回馈了失败的情况
				String resultMessage = CMPP2ResultUtil.getResultMessage(result);
				submit.setReportState(SmsConstant.REPORT_FAIL);
				submit.setErrorMsg(resultMessage);
			} else {
				// 如果没进到if中  说明运营商已经正常的接受了发送短信的任务  继续完成操作三
				// 3、将submit封装为report临时存储  以便于运营商返回状态码时可以再次获取到信息
				// 这里没有对其他信息进行封装
				report = new StandardReport();
				BeanUtils.copyProperties(submit, report);
			}
			
			// 4、将封装好的任务放到线程池中执行即可
			System.out.println("线程池处理");
			
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
			} else {
				//用户回复会打印在这里
				log.info("{}", MsgUtils.bytesToLong(resp.getMsg_Id()));
				log.info(resp.getSrc_terminal_Id());
				log.info(resp.getMsg_Content());
			}
		}
	}
	
}
