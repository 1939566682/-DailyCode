package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.PublicParams;
import org.example.entity.PublicParamsExample;
import org.example.mapper.PublicParamsMapper;
import org.example.service.PublicParamsService;
import org.example.util.PageResult;
import org.example.vo.PublicParamsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PublicParamsServiceImpl implements PublicParamsService {
    @Autowired
    private PublicParamsMapper publicParamsMapper;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public PageResult<PublicParamsVO> list(int offset, int limit, String search) {
        PublicParamsExample example = new PublicParamsExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andparamNameLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<PublicParams> list = publicParamsMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<PublicParamsVO> voList = new ArrayList<>();
        for (PublicParams pp : list) {
            PublicParamsVO vo = toVO(pp);
            voList.add(vo);
        }
        return new PageResult<>(total, voList);
    }

    private PublicParamsVO toVO(PublicParams pp) {
        PublicParamsVO vo = new PublicParamsVO();
        vo.setId(pp.getId());
        vo.setParamName(pp.getParamName());
        vo.setParamType(pp.getParamType());
        if (pp.getCreateDate() != null) {
            vo.setCreateDate(sdf.format(pp.getCreateDate()));
        }
        vo.setDescripton(pp.getDescription());
        vo.setIsMust(pp.getIsMust());
        vo.setEnableState(pp.getEnableState());
        return vo;
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            publicParamsMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PublicParamsVO findById(Long id) {
        PublicParams pp = publicParamsMapper.selectByPrimaryKey(id);
        if (pp == null) return null;
        return toVO(pp);
    }

    @Override
    public void save(PublicParamsVO vo) {
        PublicParams pp = new PublicParams();
        pp.setParamName(vo.getParamName());
        pp.setParamType(vo.getParamType());
        pp.setDescription(vo.getDescripton());
        pp.setIsMust(vo.getIsMust());
        pp.setEnableState(vo.getEnableState());
        pp.setCreateDate(new Date());
        pp.setCreated(new Date());
        publicParamsMapper.insertSelective(pp);
    }

    @Override
    public void update(PublicParamsVO vo) {
        PublicParams pp = publicParamsMapper.selectByPrimaryKey(vo.getId());
        if (pp == null) return;
        pp.setParamName(vo.getParamName());
        pp.setParamType(vo.getParamType());
        pp.setDescription(vo.getDescripton());
        pp.setIsMust(vo.getIsMust());
        pp.setEnableState(vo.getEnableState());
        pp.setUpdated(new Date());
        publicParamsMapper.updateByPrimaryKeySelective(pp);
    }
}
