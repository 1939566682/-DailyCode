package org.example.dao;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 11:38
 */

public interface AccountDao {

    /**
     * 收入
     * @param accountId  收款账户ID
     * @param money  收入金额
     * @return
     */
    Integer inCome(Integer accountId,Double money);

    /**
     * 支出
     * @param accountId 付款账户ID
     * @param money 支出金额
     * @return
     */
    Integer outCome(Integer accountId,Double money);
}
