package org.example.controller;

import org.example.common.Result;
import org.example.service.DistrictService;
import org.example.vo.DistrictVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:17
 */

@RequestMapping("/district")
@RestController
public class DistrictController {

    @Autowired
    DistrictService districtService;

    /**
     * 查询所有区域信息
     * 请求方法：Get
     * 请求路径：http://localhost:8080/district/list
     */
    @GetMapping("/list")
    public ResultVO<List<DistrictVO>> getDistrictList() {
        List<DistrictVO> list = districtService.getDistrictList();
        return Result.success(list);
    }

}
