package org.example.luoYu.test;

import org.apache.log4j.Logger;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-16 14:53
 */

public class TestLog4j {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(TestLog4j.class);
        logger.fatal("fatal message");
        logger.error("error message");
        logger.warn("warn message");
        logger.info ("info message");
        logger.debug ("debug message");


        // 打印异常信息
        try {
            int i =1/0;
        } catch (Exception e) {
            logger.warn("程序捕获到了异常",e);
        }
    }
}
