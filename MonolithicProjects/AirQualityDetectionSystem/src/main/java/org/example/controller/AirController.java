package org.example.controller;

import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Delete;
import org.example.common.Result;
import org.example.dto.AirAddDTO;
import org.example.dto.AirUpdateDTO;
import org.example.service.AirService;
import org.example.vo.AirVO;
import org.example.vo.PageResultVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:55
 */

@RestController
@RequestMapping("/air")
public class AirController {

    @Autowired
    private AirService airService;

    /**
     * 分页条件查询空气质量信息
     * 请求方式：Get
     * 请求路径：http://localhost:8080/air/list
     */
    @GetMapping("/list")
    public ResultVO<PageResultVO<AirVO>> getAirList(Integer districtId,
                                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                                    @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo<AirVO> airList = airService.getAirListByDistrictId(districtId, pageNum, pageSize);

        PageResultVO<AirVO> result = new PageResultVO<>();
        result.setTotal(airList.getTotal());
        result.setList(airList.getList());

        return Result.success(result);
    }

    /**
     * 添加空气质量信息
     * 请求方式：Post
     * 请求路径：http://localhost:8080/air/add
     * 请求参数：
     * districtId = Integer   （必传项）
     * monitorTime = yyyy-MM-dd  （必传项）
     * pm10 = Integer  （必传项）
     * pm25 = Integer  （必传项）
     * monitoringStation = String  （必传项）
     */
    @PostMapping("/add")
    public ResultVO<AirVO> addAir(@Valid AirAddDTO airAddDTO, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail(400, result.getAllErrors().get(0).getDefaultMessage());
        }
        airService.addAir(airAddDTO);
        return Result.success();
    }

    /**
     * 修改空气质量信息
     * 请求方式：Put
     * 请求路径：http://localhost:8080/air/update
     * 请求参数：
     * id = Integer   （必传项）
     * districtId = Integer   （非必传）
     * monitorTime = yyyy-MM-dd  （非必传）
     * pm10 = Integer  （非必传）
     * pm25 = Integer  （非必传）
     */
    @PutMapping("/update")
    public ResultVO<AirVO> updateAir(AirUpdateDTO airUpdateDTO) {
        if (airUpdateDTO.getId() == null) {
            return Result.fail(400, "参数不合法，空气质量记录编号ID不能为空！");
        }
        airService.updateAirById(airUpdateDTO);
        return Result.success();
    }

    /**
     * 删除空气质量信息
     * 请求方式：DELETE
     * 请求路径：http://localhost:8080/air/delete/{id}
     * 请求参数：
     *  路径上的id
     */
    @DeleteMapping("/delete/{id}")
    public ResultVO<AirVO> deleteAirById(@PathVariable Integer id) {
        airService.deleteAirById(id);
        return Result.success();
    }
}
