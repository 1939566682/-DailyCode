package org.example.task;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TestTask
 *
 * @author Yang QingBo
 * @date 2026-06-13 19:38
 * @description
 */

@Slf4j
@Component
public class TestTask {

	@XxlJob("test")
	public void test(){
		log.info("Hello World!");
	}

}
