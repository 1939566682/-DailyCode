package org.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 09:30
 */

// 使用Spring整合Junit4的类启动当前测试类 （这个注解必须要用4.12以上版本的junit）
// RunWith、ContextConfiguration这些注解不用扫描，@RunWith就会自动去构建测试类对象
@RunWith(SpringJUnit4ClassRunner.class)
// 启动时加载的配置文件，里面要包含classpath
@ContextConfiguration(locations = "classpath:applicationcontext.xml")
public class SpringJdbcTest02 {
    // 将容器中的jdbcTemplate注入进来
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testQueryCount() {
        // 通过模板进行CRUD操作
        // 定义sql语句:案例：查询总计有多少个账户
        String sql = "select count(1) from tb_account";
        // 执行查询操作（无参数）
        Integer total= jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("总记录数:" + total);
    }

    @Test
    public void testQueryCountByUserId() {
        // 通过模板进行CRUD操作
        // 定义sql语句:案例：查询指定用户有多少个账户
        String sql = " select count(1) from tb_account where user_id = ?";
        // 执行查询操作（有参数）第一个参数
        Integer total = jdbcTemplate.queryForObject(sql, Integer.class, 1);
        System.out.println("总记录数:" + total);
    }
}
