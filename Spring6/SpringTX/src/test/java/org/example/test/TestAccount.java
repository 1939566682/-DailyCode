package org.example.test;

import org.example.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-25 11:49
 */

public class TestAccount extends BaseTest {
    @Autowired
    AccountService accountService;

    @Test
    public void TransferTest() {
        Integer code = accountService.transfer(1, 2, 100.0);
        if (code == 1) {
            System.out.println("转账成功");
        } else System.out.println("转账失败");
    }

}
