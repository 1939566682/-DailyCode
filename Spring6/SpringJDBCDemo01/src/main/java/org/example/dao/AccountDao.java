package org.example.dao;



import org.example.entity.Account;

import java.util.List;
/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 09:45
 */

/**
 * 用户模块 接口定义
 * 1. 添加账户
 * 添加账户记录，返回受影响的行数
 * 添加账户记录，返回记录的主键
 * 批量添加账户记录，返回受影响的行数
 * 2. 查询账户
 * 查询指定用户的账户总记录数，返回该记录数
 * 通过账户主键id，查询指定账户的记录详情，返回账户信息
 * 多条件查询指定用户的账户列表，返回账户集合
 * 3. 更新账户
 * 更新账户记录，返回受影响的行数
 * 批量更新账户记录，返回受影响的行数
 * 4. 删除账户
 * 删除账户记录，返回受影响的行数
 * 批量删除账户记录，返回受影响的行数
 */
public interface AccountDao {
    // 添加操作：添加账户记录，返回受影响的行数：
    int addAccount(Account account);

    // 添加操作：添加账户记录，返回记录的主键：
    int addAccountReturnKey(Account account);

    // 添加操作：批量添加账户记录，返回受影响的行数：
    int addAccountBatch(List<Account> accounts);

    // 查询操作：查询指定用户的账户总记录数，返回账户总记录数：
    int queryAccountCount(Integer userId);

    // 查询操作：通过账户的主键id，查询对应的账户的信息：
    Account queryAccountById(Integer accountId);

    /**
     * 查询操作：多条件查询指定用户的账户列表，返回账户集合：
     *
     * @param userId      指定的账户的id
     * @param accountName 账户名称 （利用模糊查询）
     * @param createTime  创建时间  （查询大于当前时间）
     */
    List<Account> queryAccountByParams(Integer userId, String accountName, String createTime);

    // 更新操作：更新账户记录，返回受影响的行数：
    int updateAccountById(Account account);

    // 更新操作：批量更新账户记录，返回受影响的行数：
    int updateAccountBatch(List<Account> accounts);

    // 删除操作：根据主键id删除对应的账户，返回受影响的行数：
    int deleteAccountById(Integer accountId);

    // 删除操作：批量删除-根据主键id删除对应的账户，返回受影响的行数：
    int deleteAccountBatch(Integer[] accountIds);

}