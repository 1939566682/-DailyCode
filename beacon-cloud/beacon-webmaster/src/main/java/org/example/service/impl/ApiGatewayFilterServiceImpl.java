package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.ApiGatewayFilter;
import org.example.entity.ApiGatewayFilterExample;
import org.example.mapper.ApiGatewayFilterMapper;
import org.example.service.ApiGatewayFilterService;
import org.example.util.PageResult;
import org.example.util.VOConverter;
import org.example.vo.ApiGatewayFilterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ApiGatewayFilterServiceImpl implements ApiGatewayFilterService {
    @Autowired
    private ApiGatewayFilterMapper apiGatewayFilterMapper;

    @Override
    public PageResult<ApiGatewayFilterVO> list(int offset, int limit, String search) {
        ApiGatewayFilterExample example = new ApiGatewayFilterExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andfiltersLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<ApiGatewayFilter> list = apiGatewayFilterMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<ApiGatewayFilterVO> voList = VOConverter.toVOList(list, ApiGatewayFilterVO.class);
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            apiGatewayFilterMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public ApiGatewayFilterVO findById(Long id) {
        ApiGatewayFilter entity = apiGatewayFilterMapper.selectByPrimaryKey(id);
        return VOConverter.toVO(entity, ApiGatewayFilterVO.class);
    }

    @Override
    public void save(ApiGatewayFilterVO vo) {
        ApiGatewayFilter entity = VOConverter.toEntity(vo, ApiGatewayFilter.class);
        entity.setCreated(new Date());
        apiGatewayFilterMapper.insertSelective(entity);
    }

    @Override
    public void update(ApiGatewayFilterVO vo) {
        ApiGatewayFilter entity = VOConverter.toEntity(vo, ApiGatewayFilter.class);
        entity.setUpdated(new Date());
        apiGatewayFilterMapper.updateByPrimaryKeySelective(entity);
    }
}
