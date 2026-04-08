package org.example.log;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * LogDemo
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-08 09:35
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogDemo {

    @Test
    public void test1(){
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Hello World");
        logger.info("Hello LoggerBack");
    }
}
