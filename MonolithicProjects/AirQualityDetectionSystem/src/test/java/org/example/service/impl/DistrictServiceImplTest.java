package org.example.service.impl;

import org.example.service.DistrictService;
import org.example.vo.DistrictVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:48
 */

@SpringBootTest
class DistrictServiceImplTest {
    @Autowired
    private DistrictService districtService;

    @Test
    void list() {
        List<DistrictVO> list = districtService.getDistrictList();
        System.out.println(Arrays.toString(list.toArray()));
    }
}