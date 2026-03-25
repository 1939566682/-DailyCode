package org.example.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 09:40
 */

// 使用Spring整合Junit4的类启动当前测试类 （这个注解必须要用4.12以上版本的junit）
// RunWith、ContextConfiguration这些注解不用扫描，@RunWith就会自动去构建测试类对象
@RunWith(SpringJUnit4ClassRunner.class)
// 启动时加载的配置文件，里面要包含classpath
@ContextConfiguration(locations = "classpath:applicationcontext.xml")
public class BaseTest {

}