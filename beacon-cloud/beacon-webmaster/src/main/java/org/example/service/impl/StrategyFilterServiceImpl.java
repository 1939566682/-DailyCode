package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.StrategyFilter;
import org.example.entity.StrategyFilterExample;
import org.example.mapper.StrategyFilterMapper;
import org.example.service.StrategyFilterService;
import org.example.util.PageResult;
import org.example.util.VOConverter;
import org.example.vo.StrategyFilterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class StrategyFilterServiceImpl implements StrategyFilterService {
    @Autowired
    private StrategyFilterMapper strategyFilterMapper;

    @Override
    public PageResult<StrategyFilterVO> list(int offset, int limit, String search) {
        StrategyFilterExample example = new StrategyFilterExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andfiltersLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<StrategyFilter> list = strategyFilterMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<StrategyFilterVO> voList = VOConverter.toVOList(list, StrategyFilterVO.class);
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            strategyFilterMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public StrategyFilterVO findById(Long id) {
        StrategyFilter entity = strategyFilterMapper.selectByPrimaryKey(id);
        return VOConverter.toVO(entity, StrategyFilterVO.class);
    }

    @Override
    public void save(StrategyFilterVO vo) {
        StrategyFilter entity = VOConverter.toEntity(vo, StrategyFilter.class);
        entity.setCreated(new Date());
        strategyFilterMapper.insertSelective(entity);
    }

    @Override
    public void update(StrategyFilterVO vo) {
        StrategyFilter entity = VOConverter.toEntity(vo, StrategyFilter.class);
        entity.setUpdated(new Date());
        strategyFilterMapper.updateByPrimaryKeySelective(entity);
    }
}
