package org.example.mapper;

import org.example.vo.AirVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 19:45
 */

@SpringBootTest
class AirMapperTest {

    @Autowired
    AirMapper airMapper;

    @Test
    void getAirListByDistrictId() {
        List<AirVO> airListByDistrictId = airMapper.getAirListByDistrictId(1);
        for (AirVO airVO : airListByDistrictId) {
            System.out.println(airVO);
        }
    }
}