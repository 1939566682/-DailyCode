package org.example.log;

import lombok.extern.slf4j.Slf4j;
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


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogDemo {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 传统方式打印日志
     */
    @Test
    public void test1(){
        logger.trace("Hello World");
        logger.debug("Hello World");
        logger.info("Hello World");
        logger.warn("Hello World");
        logger.error("Hello World");
    }

    /**
     * Slf4j 注解方式打印日志
     */
    @Test
    public void test2(){
        log.trace("Hello World");
        log.debug("Hello World");
        log.info("Hello World");
        log.warn("Hello World");
        log.error("Hello World");
    }

    @Test
    public void test3(){
        String test = "Hello World";
        log.info("xxxx {} xxxx"+test);
        log.info("xxxx {} xxxx",test);
        log.info("xxxx {} xxxx {}",test,test);
    }
}
