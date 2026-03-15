package org.example.luoYu.test1;

import org.example.luoYu.entity.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-15 11:04
 */

public class TestJDBC5 {
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String user="root";
    private static final String password="root";

    public static void main(String[] args) {

        List<Emp> emps = testQuery();
        // 遍历集合
        for (Emp emp : emps) {
            System.out.println(emp);
        }


    }

    public static List<Emp> testQuery() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Emp> list = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            String sql = "select * from emp";
            resultSet = statement.executeQuery(sql);

            list = new ArrayList<>();
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
            if (null != statement) {
                try {
                    statement.close();
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
        return list;
    }


}
