package org.example.luoYu.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-15 10:34
 */

public class TestJDBC2 {
    public static void main(String[] args) throws Exception {

        //1加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String user="root";
        String password="root";
        //2 获取连接
        Connection connection = DriverManager.getConnection(url, user,password);
        //3获得语句对象 Statement
        Statement statement = connection.createStatement();
        //3执行SQL语句,返回结果

        String sql="insert into dept values(51 ,'助教部门','北京');";
        int rows = statement.executeUpdate(sql);
        System.out.println("影响数据行数为:"+rows);
        //4释放资源
        statement.close();
        connection.close();


    }
}
