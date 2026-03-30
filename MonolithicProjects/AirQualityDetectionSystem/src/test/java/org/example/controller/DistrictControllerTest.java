package org.example.controller;

import org.example.vo.DistrictVO;
import org.example.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:50
 */

@SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DistrictControllerTest {
    @Autowired
    DistrictController districtController;

    @Test
    void list() {
        ResultVO<List<DistrictVO>> list = districtController.getDistrictList();
        System.out.println(list.getData());
    }
}