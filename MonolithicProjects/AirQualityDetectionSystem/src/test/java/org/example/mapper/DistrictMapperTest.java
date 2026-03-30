package org.example.mapper;

import org.example.vo.DistrictVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:31
 */

@SpringBootTest
class DistrictMapperTest {

    @Autowired
    private DistrictMapper mapper;

    @Test
    void list() {
        List<DistrictVO> list = mapper.list();
        System.out.println(list);
    }
}