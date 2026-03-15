package org.example.luoYu.test2;

import org.example.luoYu.entity.Account;

import java.sql.*;
import java.util.Scanner;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-15 11:59
 */

public class TestInjection2 {
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String user="root";
    private static final String password="root";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        System.out.println("请输入密码");
        String pwd = sc.next();


        Account account = getAccount(username, pwd);
        System.out.println(null != account ? "登录成功" : "登录失败");
        sc.close();
    }


    public static Account getAccount(String username, String pwd) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Account account = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            /*
             * 1 使用PreparedStatement语句对象防止注入攻击
             * 2 PreparedStatement 可以使用 ? 作为参数的占位符
             * 3 使用?作为占位符,即使是字符串和日期类型,也不使用单独再添加 ''
             * 4 connection.createStatement();获得的是普通语句对象 Statement
             * 5 connection.prepareStatement(sql);可以获得一个预编译语句对象PreparedStatement
             * 6 如果SQL语句中有?作为参数占位符号,那么要在执行CURD之前先设置参数
             * 7 通过set***(问号的编号,数据) 方法设置参数
             * */
            String sql = "select * from account where username = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);// 这里已经传入SQL语句
            // 设置参数
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pwd);

            // 执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句


            while (resultSet.next()) {
                int aid = resultSet.getInt("aid");
                String usernamea = resultSet.getString("username");
                String pwda = resultSet.getString("password");
                double money = resultSet.getDouble("money");
                account = new Account(aid, usernamea, pwda, money);
                System.out.println(account);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }

            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return account;

    }
}
