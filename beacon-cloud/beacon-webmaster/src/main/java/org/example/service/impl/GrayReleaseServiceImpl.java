package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.GrayRelease;
import org.example.entity.GrayReleaseExample;
import org.example.mapper.GrayReleaseMapper;
import org.example.service.GrayReleaseService;
import org.example.util.PageResult;
import org.example.util.VOConverter;
import org.example.vo.GrayReleaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class GrayReleaseServiceImpl implements GrayReleaseService {
    @Autowired
    private GrayReleaseMapper grayReleaseMapper;

    @Override
    public PageResult<GrayReleaseVO> list(int offset, int limit, String search) {
        GrayReleaseExample example = new GrayReleaseExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andserviceIdLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<GrayRelease> list = grayReleaseMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<GrayReleaseVO> voList = VOConverter.toVOList(list, GrayReleaseVO.class);
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            grayReleaseMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public GrayReleaseVO findById(Long id) {
        GrayRelease entity = grayReleaseMapper.selectByPrimaryKey(id);
        return VOConverter.toVO(entity, GrayReleaseVO.class);
    }

    @Override
    public void save(GrayReleaseVO vo) {
        GrayRelease entity = VOConverter.toEntity(vo, GrayRelease.class);
        entity.setCreated(new Date());
        grayReleaseMapper.insertSelective(entity);
    }

    @Override
    public void update(GrayReleaseVO vo) {
        GrayRelease entity = VOConverter.toEntity(vo, GrayRelease.class);
        entity.setUpdated(new Date());
        grayReleaseMapper.updateByPrimaryKeySelective(entity);
    }
}
