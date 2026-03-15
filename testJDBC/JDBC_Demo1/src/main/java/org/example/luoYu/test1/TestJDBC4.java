package org.example.luoYu.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-15 10:58
 */

public class TestJDBC4 {
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String user="root";
    private static final String password="root";

    public static void main(String[] args)  {
        testDelete();
        //testUpdate();
    }
    public static void testUpdate(){
        Connection connection=null;
        Statement statement=null;

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,password);
            statement = connection.createStatement();
            String sql="update dept set dname='总部',loc='北京' where deptno= 30 ";
            int rows = statement.executeUpdate(sql);
            System.out.println("影响数据行数为:"+rows);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != statement){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testDelete(){
        Connection connection=null;
        Statement statement=null;

        try{
            Class.forName(driver);
            connection =DriverManager.getConnection(url, user,password);
            statement = connection.createStatement();
            String sql="delete from dept where deptno =40";
            int rows = statement.executeUpdate(sql);
            System.out.println("影响数据行数为:"+rows);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != statement){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
