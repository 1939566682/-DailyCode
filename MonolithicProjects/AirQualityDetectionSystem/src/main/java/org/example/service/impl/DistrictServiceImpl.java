package org.example.service.impl;

import org.example.mapper.DistrictMapper;
import org.example.service.DistrictService;
import org.example.vo.DistrictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:43
 */

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<DistrictVO> getDistrictList() {
        return districtMapper.list();
    }
}
