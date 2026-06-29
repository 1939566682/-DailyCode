package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.SearchParams;
import org.example.entity.SearchParamsExample;
import org.example.mapper.SearchParamsMapper;
import org.example.service.SearchParamsService;
import org.example.util.PageResult;
import org.example.vo.SearchParamsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchParamsServiceImpl implements SearchParamsService {
    @Autowired
    private SearchParamsMapper searchParamsMapper;

    @Override
    public PageResult<SearchParamsVO> list(int offset, int limit, String search) {
        SearchParamsExample example = new SearchParamsExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andnameLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<SearchParams> list = searchParamsMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<SearchParamsVO> voList = new ArrayList<>();
        for (SearchParams sp : list) {
            SearchParamsVO vo = new SearchParamsVO();
            vo.setId(sp.getId());
            vo.setName(sp.getName());
            vo.setCloum(sp.getColumnName());
            vo.setType(sp.getType());
            vo.setTOrder(sp.getTOrder());
            vo.setState(sp.getState());
            voList.add(vo);
        }
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            searchParamsMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public SearchParamsVO findById(Long id) {
        SearchParams sp = searchParamsMapper.selectByPrimaryKey(id);
        if (sp == null) return null;
        SearchParamsVO vo = new SearchParamsVO();
        vo.setId(sp.getId());
        vo.setName(sp.getName());
        vo.setCloum(sp.getColumnName());
        vo.setType(sp.getType());
        vo.setTOrder(sp.getTOrder());
        vo.setState(sp.getState());
        return vo;
    }

    @Override
    public void save(SearchParamsVO vo) {
        SearchParams sp = new SearchParams();
        sp.setName(vo.getName());
        sp.setColumnName(vo.getCloum());
        sp.setType(vo.getType());
        sp.setTOrder(vo.getTOrder());
        sp.setState(vo.getState());
        sp.setCreated(new Date());
        searchParamsMapper.insertSelective(sp);
    }

    @Override
    public void update(SearchParamsVO vo) {
        SearchParams sp = searchParamsMapper.selectByPrimaryKey(vo.getId());
        if (sp == null) return;
        sp.setName(vo.getName());
        sp.setColumnName(vo.getCloum());
        sp.setType(vo.getType());
        sp.setTOrder(vo.getTOrder());
        sp.setState(vo.getState());
        sp.setUpdated(new Date());
        searchParamsMapper.updateByPrimaryKeySelective(sp);
    }
}
