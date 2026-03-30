package org.example.service.impl;

import com.github.pagehelper.PageInfo;
import org.example.service.AirService;
import org.example.vo.AirVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 20:03
 */

@SpringBootTest
class AirServiceImplTest {

    @Autowired
    private AirService airService;

    @Test
    void getAirListByDistrictId() {
        PageInfo<AirVO> airList = airService.getAirListByDistrictId(1, 1, 5);
        for (AirVO airVO : airList.getList()) {
            System.out.println(airVO);
        }
    }
}