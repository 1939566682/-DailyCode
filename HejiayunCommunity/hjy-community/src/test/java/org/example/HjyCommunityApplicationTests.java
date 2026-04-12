package org.example;

import org.example.hjycommunity.system.domain.pojo.SysUser;
import org.example.hjycommunity.system.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HjyCommunityApplicationTests {

    @Test
    void contextLoads() {
    }
    
    @Autowired
    SysUserMapper userMapper;
    
    @Test
    public void testSelectUserByUserName(){
        SysUser admin = userMapper.selectUserByUserName("admin");
        System.out.println(admin);
    }
    
}
