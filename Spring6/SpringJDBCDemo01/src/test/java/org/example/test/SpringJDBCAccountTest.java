package org.example.test;

import org.example.dao.AccountDao;
import org.example.entity.Account;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 09:49
 */

public class SpringJDBCAccountTest extends BaseTest {
    // 注入持久层对象（dao层）对象
    @Autowired
    AccountDao accountDao;

    @Test
    public void testAddAccount() {
        // 准备你要添加的数据：
        Account account = new Account("账号3", "工商银行", 400.0, "奖金", 1);
        // 调用AccountDaoImpl对象的addAccount方法，传入上面要添加的数据account：
        int row = accountDao.addAccount(account);
        System.out.println("添加账户，受影响的行数为：" + row);
    }

    /**
     * 添加账户记录，返回主键
     */
    @Test
    public void testAddAccount2() {
        // 准备你要添加的数据：
        Account account = new Account("账号4", "招商银行", 1400.0, "兼职费用", 2);
        // 调用AccountDaoImpl对象的addAccount方法，传入上面要添加的数据account：
        int key = accountDao.addAccountReturnKey(account);
        System.out.println("对应的主键为：" + key);
    }

    /**
     * 批量添加数据，返回受影响的行数
     */
    @Test
    public void testAddAccountBatch() {
        // 准备你要添加的数据：
        Account account1 = new Account("账号8", "招商银行", 13450.0, "兼职费用", 3);
        Account account2 = new Account("账号9", "工商银行", 1670.0, "奖金", 3);
        Account account3 = new Account("账号10", "建设银行", 7400.0, "工资", 3);

        // 上面的三个账户添加到集合中，以便以后传入批量添加的方法中：
        List<Account> accouts = new ArrayList<>();
        accouts.add(account1);
        accouts.add(account2);
        accouts.add(account3);

        // 调用AccountDaoImpl对象的addAccountBatch方法，传入上面要添加的数据accouts：
        int row = accountDao.addAccountBatch(accouts);
        System.out.println("添加账户，受影响的行数为：" + row);
    }

    /**
     * 查询用户的账户总记录数，返回总记录数
     */
    @Test
    public void testQueryAccountCount() {
        // 查询ID为1的用户的账户总记录数
        int total = accountDao.queryAccountCount(1);
        System.out.println("总记录数：" + total);
    }

    /**
     * 查询指定账户的记录详情，返回账户对象
     */
    @Test
    public void testQuery02() {
        // 调用accountDao中的queryAccountById：查询操作：通过账户的主键id，查询对应的账户的信息：
        Account account = accountDao.queryAccountById(10);
        System.out.println("该用户的账户信息为：" + account.toString());
    }

    /**
     * 查询操作：多条件查询指定用户的账户列表，返回账户集合：
     * userId 指定的账户的用户id
     * accountName 账户名称 （利用模糊查询）
     * createTime 创建时间  （查询大于当前时间）
     */
    @Test
    public void testQuery03() {
        // 调用accountDao中的queryAccountByParams：
        List<Account> accountList = accountDao.queryAccountByParams(1, null, null);

        /*System.out.println(accountList.toString());
        System.out.println(accountList.size());*/

        List<Account> accountList2 = accountDao.queryAccountByParams(1, "1", null);

        /*System.out.println(accountList2.toString());
        System.out.println(accountList2.size());*/

        List<Account> accountList3 = accountDao.queryAccountByParams(1, null, "2023-3-20");

        System.out.println(accountList3.toString());
        System.out.println(accountList3.size());


    }

    /**
     * 更新指定账户记录，返回受影响的行数
     */
    @Test
    public void testUpdateAccount() {
        // 准备要修改的数据
        Account account = new Account("张三1", "建设银行1", 500.0, "零花钱加倍", 1);
        account.setAccountId(1);
        int row = accountDao.updateAccountById(account);
        System.out.println("修改账户返回受影响的行数：" + row);
    }

    /**
     * 批量更新账户记录，返回受影响的行数
     */
    @Test
    public void testUpdate02() {
        // 调用accountDao中的updateAccountBatch
        // 封装Account对象：
        Account ac1 = new Account("账户66", "农业银行", 220.0, "奖金", 1);
        ac1.setAccountId(9);
        Account ac2 = new Account("账户77", "农业银行", 240.0, "奖金", 1);
        ac2.setAccountId(10);
        Account ac3 = new Account("账户88", "农业银行", 290.0, "奖金", 1);
        ac3.setAccountId(11);

        // 上面的数据要放入List集合中：
        List list = new ArrayList();
        list.add(ac1);
        list.add(ac2);
        list.add(ac3);


        int row = accountDao.updateAccountBatch(list);
        System.out.println("更新操作影响行数为：" + row);


    }

    /**
     * 删除账户记录，返回受影响的行数
     */
    @Test
    public void testDeleteAccount() {
        // 删除ID为1的账户记录
        int row = accountDao.deleteAccountById(1);
        System.out.println("删除账户记录返回受影响的行数：" + row);
    }

    /**
     * 批量删除账户记录，返回受影响的行数
     */
    @Test
    public void testDeleteAccountBatch() {
        // 删除多个id的账户记录
        Integer[] ids = new Integer[]{2, 3};
        int rows = accountDao.deleteAccountBatch(ids);
        System.out.println("批量删除账户记录返回受影响的行数：" + rows);
    }
}