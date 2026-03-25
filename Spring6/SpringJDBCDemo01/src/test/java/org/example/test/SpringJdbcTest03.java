package org.example.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 09:40
 */

public class SpringJdbcTest03 extends BaseTest {

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