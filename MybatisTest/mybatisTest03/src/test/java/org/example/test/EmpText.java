package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author Yang QingBo
 * {@code @date} 2026-03-22 18:07
 */

public class EmpText {
    private static final Logger log = LogManager.getLogger(EmpText.class);
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession session;

    // @BeforeAll 方法必须是 static（JUnit 5 要求）
    @BeforeAll
    public static void initSqlSession() {
        // 初始化 SqlSessionFactory（重量级资源，全局仅创建1次）
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = null;
        try {
            // 加载 MyBatis 核心配置文件
            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            sqlSessionFactory = ssfb.build(resourceAsStream);
            // 打开 SqlSession（默认不自动提交事务）
            session = sqlSessionFactory.openSession(true); // true 表示自动提交事务
        } catch (IOException e) {
            // 规范异常处理：打印异常并终止程序
            e.printStackTrace();
            throw new RuntimeException("MyBatis 配置文件加载失败！", e);
        } finally {
            // 关闭输入流资源
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testOneToOne() {
        // 校验 session 是否初始化成功
        if (session == null) {
            throw new RuntimeException("SqlSession 初始化失败！");
        }
        EmpMapper empMapper = session.getMapper(EmpMapper.class);
        Emp emp = empMapper.findEmpJoinDeptByEmpno(7499);
        System.out.println(emp);
    }


    // @AfterAll：所有测试方法执行完后，统一关闭资源
    @AfterAll
    public static void closeResource() {
        if (session != null) {
            session.close();
            System.out.println("SqlSession 已关闭！");
        }
    }
}
