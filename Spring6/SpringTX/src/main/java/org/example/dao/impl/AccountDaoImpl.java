package org.example.dao.impl;

import org.example.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 11:41
 */

@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jt;

    @Override
    public Integer inCome(Integer accountId, Double money) {
        // 定义SQL
        String sql = "update tb_account set money=money+? where account_id=?";
        // 定义参数列表
        Object[] params = {money,accountId};
        // 使用模版
        return jt.update(sql, params);
    }

    @Override
    public Integer outCome(Integer accountId, Double money) {
        // 定义SQL
        String sql = "update tb_account set money=money-? where account_id=?";
        // 定义参数列表
        Object[] params = {money,accountId};
        // 使用模版
        return jt.update(sql, params);
    }
}
