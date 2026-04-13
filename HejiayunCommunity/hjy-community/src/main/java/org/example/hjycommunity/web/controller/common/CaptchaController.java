package org.example.hjycommunity.web.controller.common;

import com.wf.captcha.SpecCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.example.hjycommunity.common.constant.Constants;
import org.example.hjycommunity.common.utils.ChainedMap;
import org.example.hjycommunity.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

/**
 * CaptchaController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 18:24
 */

@Slf4j
@RestController
public class CaptchaController {

	// 当 Redis 当做数据库或者消息队列来操作时 一般使用 redisTemplate 来操作
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 生成验证码
	 * @param response
	 * @return
	 */
	@GetMapping("/captchaImage")
	public ChainedMap getCode(HttpServletResponse response) {
		SpecCaptcha specCaptcha = new SpecCaptcha(130,48,4);
		
		// 生成验证码 和 验证码唯一标识
		String uuid = UUIDUtils.simpleUUID();
		String key = Constants.CAPTCHA_CODE_KEY + uuid;
		String code = specCaptcha.text().toLowerCase();
		log.info("log() getCode() key:{}",key);
		log.info("log() getCode() code:{}",code);
		
		// 保存到 redis
		redisTemplate.opsForValue().set(key,code,Duration.ofSeconds(600));
		
		return ChainedMap.create().set("uuid",uuid).set("img",specCaptcha.toBase64());
	}
}
