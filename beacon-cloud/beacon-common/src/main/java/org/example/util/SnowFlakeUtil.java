package org.example.util;

import org.example.enums.ExceptionEnums;
import org.example.execption.ApiIllegalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

@Component
public class SnowFlakeUtil {
	
	/**
	 * 41个bit位存储时间戳  从 0 开始计算  最多可以存储69.7年
	 */
	
	private final Long timeStart = 1780156800000L;
	
	/**
	 * 机器id
	 */
	@Value("${snowflake.machineId:0}")
	private final Long machineId = 0L;
	
	/**
	 * 服务id
	 */
	@Value("${snowflake.serviceId:0}")
	private final Long serviceId = 0L;
	
	/**
	 * 序列
	 */
	private long sequence = 0L;
	
	/**
	 * 机器id占据的bit位数
	 */
	private long machineIdBits = 5L;
	
	/**
	 * 服务id占据的bit位数
	 */
	private long serviceIdBits = 5L;
	
	/**
	 * 序列占用的bit位数
	 */
	private long sequenceBits = 12L;
	
	/**
	 * 计算出机器id的最大值
	 */
	private long maxMachineId = -1 ^ (-1 << machineIdBits);
	
	/**
	 * 计算出服务id的最大值
	 */
	private long maxServiceId = -1 ^ (-1 << serviceIdBits);
	
	@PostConstruct
	public void init() {
		if (machineId > maxMachineId || serviceId > maxServiceId) {
			System.out.println("机器ID或服务ID超出最大范围值");
			throw new ApiIllegalException(ExceptionEnums.SnowFlake_OUT_OF_RANGE);
		}
	}
	
	/**
	 * 服务id需要位移的位数
	 */
	private long serviceIdShift = sequenceBits;
	
	/**
	 * 机器id需要位移的位数
	 */
	private long machineIdShift = sequenceBits + serviceIdBits;
	
	/**
	 * 时间戳需要位移的位数
	 */
	private long timestampShift = sequenceBits + serviceIdBits + machineIdBits;
	
	/**
	 * 序列的最大值
	 */
	private long maxSequenceId = -1 ^ (-1 << sequenceBits);
	
	/**
	 * 记录最近一次获取id的时间
	 */
	private long lastTimestamp = -1L;
	
	/**
	 * 获取系统时间毫秒值
	 *
	 * @return
	 */
	private long timeGen() {
		return System.currentTimeMillis();
	}
	
	public synchronized long nextId() {
		// 1、拿到当前系统时间的毫秒值
		long timeStamp = timeGen();
		
		// 避免时间回拨造成出现重复的id
		if (timeStamp < lastTimestamp) {
			// 说明出现了时间回拨
			System.out.println("当前服务出现时间回拨！！！");
			throw new ApiIllegalException(ExceptionEnums.SnowFlake_TIME_BACK);
		}
		
		// 2、判断当前生成id的时间和上一次生成的时间
		if (timeStamp == lastTimestamp) {
			// 同一毫秒值生成id
			sequence = (sequence + 1) & maxSequenceId;
			// 1111 11111111 :sequence +1 ->
			// 10000 11111111
			//  1111 11111111 :maxSequenceId
			if (sequence == 0) {
				// 进入这个if 说明已经超出了sequence序列的最大取值范围
				// 需要进入下一个毫秒再回来生成具体的值
				timeStamp = timeGen();
				while (timeStamp <= lastTimestamp) {  // 为什么用<=？ 为了避免出现时间回拨问题
					// 时间还没动
					timeStamp = timeGen();
				}
			}
		} else {
			// 另一时间点生成id
			sequence = 0L;
		}
		
		// 3、重新给 lastTimestamp
		lastTimestamp = timeStamp;
		
		// 4、计算id  将几位值拼接起来  41位bit值的时间  5位的机器  5位的服务  12位的序列
		return ((timeStamp - timeStart) << timestampShift) |
				(machineId << machineIdShift) |
				(serviceId << serviceIdShift) |
				sequence &
						Long.MAX_VALUE;
	}
	
	public static void main(String[] args) {
		SnowFlakeUtil util = new SnowFlakeUtil();
		long id = util.nextId();
		System.out.println(id);
		System.out.println(Long.toBinaryString(id).length());
	}
}
