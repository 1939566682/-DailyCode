package org.example.dao.impl;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 09:46
 */

import org.example.dao.AccountDao;
import org.example.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountDaoImpl对象的构建，我们交给ioc容器去管理,加入@Repository注解：
 */
@Repository
public class AccountDaoImpl implements AccountDao {
    // 注入JdbcTemplate模板对象：
    @Autowired
    private JdbcTemplate jt;

    /**
     * 添加单条记录，返回受影响的行数
     *
     * @param account
     * @return
     */
    @Override
    public int addAccount(Account account) {
        String sql = "insert into tb_account" +
                "(account_name,account_type,money,remark,user_id,create_time,update_time)" +
                "values (?,?,?,?,?,now(),now())";
        Object[] objs = {account.getAccountName(), account.getAccountType(),
                account.getMoney(), account.getRemark(), account.getUserId()};
        return jt.update(sql, objs);
    }

    /**
     * 添加账户记录，返回主键
     * @param account
     * @return
     */
    @Override
    public int addAccountReturnKey(Account account) {
        // 添加操作：添加账户记录，返回记录的主键：
        // 定义sql:
        String sql = "insert into tb_account " +
                "(account_name,account_type,money,remark,create_time,update_time,user_id) " +
                "values (?,?,?,?,now(),now(),?)";
        // 利用模板调用方法，返回主键：
        // 定义KeyHolder对象，获取记录的主键值：
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // 利用模板调用方法:
        /*jt.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // 预编译sql语句，并设置返回主键：
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                // 设置参数：
                ps.setString(1,account.getAccountName());
                ps.setString(2,account.getAccountType());
                ps.setDouble(3,account.getMoney());
                ps.setString(4,account.getRemark());
                ps.setInt(5,account.getUserId());
                return ps;
            }
        }, keyHolder);*/

        // 利用lambda表达式的写法：
        jt.update(con -> {
            // 预编译sql语句，并设置返回主键：
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 设置参数：
            ps.setString(1, account.getAccountName());
            ps.setString(2, account.getAccountType());
            ps.setDouble(3, account.getMoney());
            ps.setString(4, account.getRemark());
            ps.setInt(5, account.getUserId());
            return ps;
        }, keyHolder);

        // 通过keyHolder对象来获取主键：
        int key = keyHolder.getKey().intValue();

        return key;
    }

    /**
     * 添加多条记录，返回受影响的行数
     *
     * @param accounts
     * @return
     */
    @Override
    public int addAccountBatch(List<Account> accounts) {
        // 添加操作：批量添加账户记录，返回受影响的行数：
        // 定义sql:
        String sql = "insert into tb_account " +
                "(account_name,account_type,money,remark,create_time,update_time,user_id) " +
                "values (?,?,?,?,now(),now(),?)";

        // 通过模板进行批量增加操作：
        int[] nums = jt.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                // 必须获取每一条记录，给每一条记录赋值：
                Account account = accounts.get(i);
                // 给参数设置值：
                ps.setString(1, account.getAccountName());
                ps.setString(2, account.getAccountType());
                ps.setDouble(3, account.getMoney());
                ps.setString(4, account.getRemark());
                ps.setInt(5, account.getUserId());
            }

            @Override
            public int getBatchSize() {
                // 可以得到一共操作多少条记录
                return accounts.size();
            }
        });
        // 获取影响几行记录：
        return nums.length;
    }

    /**
     * 查询指定用户的账户总记录数，返回记录数
     *
     * @param userId
     * @return
     */
    @Override
    public int queryAccountCount(Integer userId) {
        String sql = "select count(1) from tb_account where user_id = ?";
        int count = jt.queryForObject(sql, Integer.class, userId);
        return count;
    }

    /**
     * 查询某个账户记录详情，返回账户对象
     *
     * @param accountId
     * @return
     */
    @Override
    public Account queryAccountById(Integer accountId) {
        // 查询操作：通过账户的主键id，查询对应的账户的信息：
        // 定义sql：
        String sql = "select * from tb_account where account_id = ?";
        // 利用模板调用方法操作：
        /*Account account = jt.queryForObject(sql, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                // 创建一个Account对象，接收数据库中查询的数据：
                Account a = new Account();
                a.setAccountId(accountId);
                a.setAccountName(rs.getString("account_name"));
                a.setAccountType(rs.getString("account_type"));
                a.setMoney(rs.getDouble("money"));
                a.setRemark(rs.getString("remark"));
                a.setCreateTime(rs.getDate("create_time"));
                a.setUpdateTime(rs.getDate("update_time"));
                a.setUserId(rs.getInt("user_id"));
                return a;
            }
        }, accountId);*/
        // 利用lambda表达式简化：
        Account account = jt.queryForObject(sql, (ResultSet rs, int rowNum) -> {
            // 创建一个Account对象，接收数据库中查询的数据：
            Account a = new Account();
            a.setAccountId(accountId);
            a.setAccountName(rs.getString("account_name"));
            a.setAccountType(rs.getString("account_type"));
            a.setMoney(rs.getDouble("money"));
            a.setRemark(rs.getString("remark"));
            a.setCreateTime(rs.getDate("create_time"));
            a.setUpdateTime(rs.getDate("update_time"));
            a.setUserId(rs.getInt("user_id"));
            return a;
        }, accountId);

        return account;
    }

    /**
     * 查询操作：多条件查询指定用户的账户列表，返回账户集合：
     *
     * @param userId      指定的账户的用户id
     * @param accountName 账户名称 （利用模糊查询）
     * @param createTime  创建时间  （查询大于当前时间）
     */
    @Override
    public List<Account> queryAccountByParams(Integer userId, String accountName, String createTime) {
        // 定义sql :
        String sql = "select * from tb_account where user_id = ?";
        // 定义参数列表：案例我们不用那么多参数，只用userId先测试,参数列表可以先不定义。
        // 如果案例中有多个参数，那么就需要定义数组，然后传入可变参数位置：--->直接定义数组方案不可取，因为数组长度不能改变：
        // 将数组改为集合：
        // Object[] params = {userId};
        List params = new ArrayList();
        params.add(userId);

        // 根据参数是否为空将sql进行拼接：
        if (accountName != null && !"".equals(accountName)) {
            // 模糊查询  注意：and前面一定要加空格，否则报错
            sql += " and account_name like concat('%',?,'%')";
            params.add(accountName);
        }
        if (createTime != null && !"".equals(createTime)) {
            sql += " and create_time < ?";
            params.add(createTime);
        }

        // 将集合转为数组：
        Object[] objects = params.toArray();

        // 使用模板：
        List<Account> accountList = jt.query(sql, (ResultSet rs, int rowNum) -> {
            // 创建一个Account对象，接收数据库中查询的数据：
            // 查询出来的每个数据，利用一个Account对象做接收，然后query方法内部会将对象放入List集合中返回
            Account a = new Account();
            a.setAccountId(rs.getInt("account_id"));
            a.setAccountName(rs.getString("account_name"));
            a.setAccountType(rs.getString("account_type"));
            a.setMoney(rs.getDouble("money"));
            a.setRemark(rs.getString("remark"));
            a.setCreateTime(rs.getDate("create_time"));
            a.setUpdateTime(rs.getDate("update_time"));
            a.setUserId(rs.getInt("user_id"));
            return a;
        }, objects);


        return accountList;
    }

    /**
     * 更新指定账户记录，返回受影响的行数
     *
     * @param account
     * @return
     */
    @Override
    public int updateAccountById(Account account) {
        String sql = "update tb_account set account_name = ?, account_type = ?, " +
                " money = ? ,remark = ?,user_id = ? ,update_time = now() " +
                " where account_id = ? ";
        Object[] objs = {account.getAccountName(), account.getAccountType(),
                account.getMoney(), account.getRemark(), account.getUserId(),
                account.getAccountId()};
        return jt.update(sql, objs);
    }

    /**
     * 批量新账户记录，返回受影响的行数
     *
     * @param accounts
     * @return
     */
    @Override
    public int updateAccountBatch(List<Account> accounts) {
        // 更新操作：批量更新账户记录，返回受影响的行数：
        // 定义sql:
        String sql = "update tb_account set account_name = ?,account_type = ?,money = ?,remark = ?," +
                "update_time = now(),user_id = ? where account_id = ?";

        // 通过模板进行批量更新操作：
        int[] nums = jt.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                // 必须获取每一条记录，给每一条记录赋值：
                Account account = accounts.get(i);
                // 给参数设置值：
                ps.setString(1, account.getAccountName());
                ps.setString(2, account.getAccountType());
                ps.setDouble(3, account.getMoney());
                ps.setString(4, account.getRemark());
                ps.setInt(5, account.getUserId());
                ps.setInt(6, account.getAccountId());
            }

            @Override
            public int getBatchSize() {
                // 可以得到一共操作多少条记录
                return accounts.size();
            }
        });
        // 获取影响几行记录：
        return nums.length;
    }


    /**
     * 删除账户记录，返回受影响的行数
     *
     * @param accountId
     * @return
     */
    @Override
    public int deleteAccountById(Integer accountId) {
        String sql = "delete from tb_account where account_id= ? ";
        Object[] objs = {accountId};
        return jt.update(sql, objs);
    }

    /**
     * 批量删除账户记录，返回受影响的行数
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteAccountBatch(Integer[] ids) {
        String sql = "delete from tb_account where account_id = ?";
        int row = jt.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, ids[i]);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        }).length;
        return row;
    }
}
