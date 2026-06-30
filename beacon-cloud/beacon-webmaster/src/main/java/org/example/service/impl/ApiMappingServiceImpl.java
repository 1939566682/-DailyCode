package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.ApiMapping;
import org.example.entity.ApiMappingExample;
import org.example.mapper.ApiMappingMapper;
import org.example.service.ApiMappingService;
import org.example.util.PageResult;
import org.example.util.VOConverter;
import org.example.vo.ApiMappingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApiMappingServiceImpl implements ApiMappingService {
    @Autowired
    private ApiMappingMapper apiMappingMapper;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public PageResult<ApiMappingVO> list(int offset, int limit, String search) {
        ApiMappingExample example = new ApiMappingExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andsourcePathLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<ApiMapping> list = apiMappingMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<ApiMappingVO> voList = new ArrayList<>();
        for (ApiMapping am : list) {
            ApiMappingVO vo = toVO(am);
            voList.add(vo);
        }
        return new PageResult<>(total, voList);
    }

    private ApiMappingVO toVO(ApiMapping am) {
        ApiMappingVO vo = new ApiMappingVO();
        vo.setId(am.getId());
        vo.setGatewayApiName(am.getSourcePath());
        vo.setServiceId(am.getExtend1());
        vo.setInsideApiUrl(am.getTargetPath());
        vo.setSourcePath(am.getSourcePath());
        vo.setTargetPath(am.getTargetPath());
        vo.setMethod(am.getMethod());
        vo.setState(am.getState());
        vo.setDescription(am.getExtend2());
        if (am.getCreated() != null) {
            vo.setCreateDate(sdf.format(am.getCreated()));
        }
        return vo;
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            apiMappingMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public ApiMappingVO findById(Long id) {
        ApiMapping am = apiMappingMapper.selectByPrimaryKey(id);
        if (am == null) return null;
        return toVO(am);
    }

    @Override
    public void save(ApiMappingVO vo) {
        ApiMapping am = new ApiMapping();
        am.setSourcePath(vo.getSourcePath() != null ? vo.getSourcePath() : vo.getGatewayApiName());
        am.setTargetPath(vo.getTargetPath() != null ? vo.getTargetPath() : vo.getInsideApiUrl());
        am.setMethod(vo.getMethod());
        am.setState(vo.getState());
        am.setExtend1(vo.getServiceId());
        am.setExtend2(vo.getDescription());
        am.setCreated(new Date());
        apiMappingMapper.insertSelective(am);
    }

    @Override
    public void update(ApiMappingVO vo) {
        ApiMapping am = apiMappingMapper.selectByPrimaryKey(vo.getId());
        if (am == null) return;
        am.setSourcePath(vo.getSourcePath() != null ? vo.getSourcePath() : vo.getGatewayApiName());
        am.setTargetPath(vo.getTargetPath() != null ? vo.getTargetPath() : vo.getInsideApiUrl());
        am.setMethod(vo.getMethod());
        am.setState(vo.getState());
        am.setExtend1(vo.getServiceId());
        am.setExtend2(vo.getDescription());
        am.setUpdated(new Date());
        apiMappingMapper.updateByPrimaryKeySelective(am);
    }
}
