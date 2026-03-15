package org.example.luoYu.test3;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-15 12:21
 */

import org.example.luoYu.entity.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestPreparedStatement {
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String user="root";
    private static final String password="root";

    public static void main(String[] args) {
        //testAdd();
        //testUpdate();
        //testDelete();
        testQuery();
    }

    public static void testAdd() {
        // 向 Emp表中增加一条数据
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "insert into emp values(DEFAULT ,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setString(1, "Mark");
            preparedStatement.setString(2, "MANAGER");
            preparedStatement.setInt(3, 7839);
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.setDouble(5, 3000.12);
            preparedStatement.setDouble(6, 0.0);
            preparedStatement.setDouble(7, 30);

            //执行CURD
            int rows = preparedStatement.executeUpdate();// 这里不需要再传入SQL语句
            System.out.println(rows);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

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
    }

    public static void testUpdate() {
        // 根据工号修改员工表中的数据
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "update emp set ename =? ,job=? where empno =?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setString(1, "Jhon");
            preparedStatement.setString(2, "ANALYST");
            preparedStatement.setInt(3, 7935);


            //执行CURD
            int rows = preparedStatement.executeUpdate();// 这里不需要再传入SQL语句
            System.out.println(rows);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

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

    }

    public static void testDelete() {
        // 根据工号删除员工表中的数据
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "delete from emp where empno =?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setInt(1, 7935);


            //执行CURD
            int rows = preparedStatement.executeUpdate();// 这里不需要再传入SQL语句
            System.out.println(rows);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

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


    }

    public static void testQuery() {
        // 查询名字中包含字母A的员工信息
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Emp> list = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            /*
             * 1使用PreparedStatement语句对象防止注入攻击
             * 2PreparedStatement 可以使用 ? 作为参数的占位符
             * 3使用?作为占位符,即使是字符串和日期类型,也不使用单独再添加 ''
             * 4connection.createStatement();获得的是普通语句对象 Statement
             * 5connection.prepareStatement(sql);可以获得一个预编译语句对象PreparedStatement
             * 6如果SQL语句中有?作为参数占位符号,那么要在执行CURD之前先设置参数
             * 7通过set***(问号的编号,数据) 方法设置参数
             * */
            String sql = "select * from emp where ename like ? ";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setString(1, "%A%");


            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句

            list = new ArrayList<Emp>();
            while (resultSet.next()) {
                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int mgr = resultSet.getInt("mgr");
                Date hiredate = resultSet.getDate("hiredate");
                double sal = resultSet.getDouble("sal");
                double comm = resultSet.getDouble("comm");
                int deptno = resultSet.getInt("deptno");
                Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
                list.add(emp);
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
        // 遍历集合
        for (Emp emp : list) {
            System.out.println(emp);
        }

    }

}
