package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.dto.AirAddDTO;
import org.example.dto.AirUpdateDTO;
import org.example.mapper.AirMapper;
import org.example.pojo.Air;
import org.example.service.AirService;
import org.example.vo.AirVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 19:16
 */
@Service
public class AirServiceImpl implements AirService {

    @Autowired
    private AirMapper airMapper;

    /**
     * 分页条件查询空气质量信息
     */
    @Override
    public PageInfo<AirVO> getAirListByDistrictId(Integer districtId, Integer pageNum, Integer pageSize) {
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        // 查询
        List<AirVO> airList = airMapper.getAirListByDistrictId(districtId);
        // 封装
        return new PageInfo<>(airList);
    }

    /**
     * 添加空气质量信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAir(AirAddDTO airAddDTO) {
        Air air = new Air();
        BeanUtils.copyProperties(airAddDTO, air);
        Integer row = airMapper.addAir(air);
        if (row != 1) {
            System.out.println("【添加空气质量】添加失败！！");
            throw new RuntimeException("【添加空气质量】添加失败！！");
        }
    }

    /**
     * 修改空气质量信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAirById(AirUpdateDTO airUpdateDTO) {
        Air air = new Air();
        BeanUtils.copyProperties(airUpdateDTO, air);
        Integer row = airMapper.updateAirById(air);
        if (row != 1) {
            System.out.println("【修改空气质量】修改失败！！");
            throw new RuntimeException("【修改空气质量】修改失败！！");
        }
    }

    /**
     * 删除空气质量信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAirById(Integer id) {
        Integer row = airMapper.deleteAirById(id);
        if (row != 1) {
            System.out.println("【删除空气质量】删除失败！！");
            throw new RuntimeException("【删除空气质量】删除失败！！");
        }
    }
}
