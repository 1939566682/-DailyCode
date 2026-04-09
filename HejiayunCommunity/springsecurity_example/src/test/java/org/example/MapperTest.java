package org.example;

import org.example.entity.SysUser;
import org.example.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MapperTest
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 15:48
 */

@SpringBootTest
public class MapperTest {
    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void testSelect() {
        List<SysUser> sysUser = sysUserMapper.selectList(null);
        sysUser.forEach(System.out::println);
    }
}
