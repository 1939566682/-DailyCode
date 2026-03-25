package org.example.test;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 09:10
 */

public class SpringJdbcTest01 {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        // 获取spring上下文环境
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 得到模板类 JdbcTemplate对象
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
    }

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