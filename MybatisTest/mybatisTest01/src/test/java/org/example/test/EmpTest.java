package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pojo.Emp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-21 15:33
 */

public class EmpTest {
    private static final Logger log = LogManager.getLogger(EmpTest.class);
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
    public void testFindOne() {
        // 校验 session 是否初始化成功
        if (session == null) {
            throw new RuntimeException("SqlSession 初始化失败！");
        }

        // 调用SQL语句
        Emp e = session.selectOne("org.example.mapper.EmpMapper.findOne");
        System.out.println(e);
    }

    @Test
    public void testSelectList() {
        // 校验 session 是否初始化成功
        if (session == null) {
            throw new RuntimeException("SqlSession 初始化失败！");
        }

        // 调用SQL语句
        List<Emp> emps = session.selectList("org.example.mapper.EmpMapper.findEmpList");
        for (Emp emp : emps) {
            System.out.println(emp);
        }
    }

    @Test
    public void testSelectMap() {
        // 校验 session 是否初始化成功
        if (session == null) {
            throw new RuntimeException("SqlSession 初始化失败！");
        }
        // 调用SQL语句
        log.info("查询多个对象的Map集合");
        Map<Integer, Emp> empMap = session.selectMap("org.example.mapper.EmpMapper.findEmpMap", "EMPNO");
        for (Map.Entry<Integer, Emp> entry : empMap.entrySet()) {
            System.out.println(entry);
        }
    }

    @Test
    public void testFindEmpByEmpno() {
        // 校验 session 是否初始化成功
        if (session == null) {
            throw new RuntimeException("SqlSession 初始化失败！");
        }
        // 调用SQL语句
        log.info("根据员工查询员工信息");
        Emp emp = session.selectOne("org.example.mapper.EmpMapper.findEmpByEmpno", 7499);
        System.out.println(emp);
    }

    @Test
    public void testFindEmpByDeptnoAndSal() {
        // 校验 session 是否初始化成功
        if (session == null) {
            throw new RuntimeException("SqlSession 初始化失败！");
        }

        HashMap<String, Integer> args = new HashMap<>();
        args.put("deptno", 10);
        args.put("sal", 600);
        // 调用SQL语句
        log.info("根据员工查询员工信息");
        List<Emp> emps = session.selectList("org.example.mapper.EmpMapper.findEmpByDeptnoAndSal", args);
        for (Emp emp : emps) {
            System.out.println(emp);
        }
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
