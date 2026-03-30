package org.example.controller;

import com.github.pagehelper.PageInfo;
import org.example.vo.AirVO;
import org.example.vo.PageResultVO;
import org.example.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 20:08
 */

@SpringBootTest
class AirControllerTest {

    @Autowired
    private AirController airController;

    @Test
    void getAirList() {
        ResultVO<PageResultVO<AirVO>> airList = airController.getAirList(1, 1, 5);
        for (AirVO airVO :
                airList.getData().getList()) {
            System.out.println(airVO);
        }
    }
}