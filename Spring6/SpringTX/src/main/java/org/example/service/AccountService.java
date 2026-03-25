package org.example.service;

import org.example.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 11:44
 */

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    // 定义业务方法：转账操作：返回值为 Integer 类型，在方法内部可以定义一个状态码：成功返回1，失败返回0

    @Transactional
    public Integer transfer(Integer outId,Integer inId,Double money){
        // 定义状态码：成功1，失败0,初始化状态下 code是0
        int code = 0;
        // 支出
        Integer outRow = accountDao.outCome(outId, money);
        // 制造异常
//        int i = 1/0;
        // 收入
        Integer inRow = accountDao.inCome(inId, money);
        // 判断是否成功
        if(outRow == 1 && inRow == 1){
            code = 1;
        }
        // 返回状态码
        return code;
    }
}
