package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.MobileDirtyword;
import org.example.entity.MobileDirtywordExample;
import org.example.mapper.MobileDirtywordMapper;
import org.example.service.DirtyWordService;
import org.example.util.PageResult;
import org.example.vo.DirtyWordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DirtyWordServiceImpl implements DirtyWordService {
    @Autowired
    private MobileDirtywordMapper mobileDirtywordMapper;

    @Override
    public PageResult<DirtyWordVO> list(int offset, int limit, String search) {
        MobileDirtywordExample example = new MobileDirtywordExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().anddirtywordLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<MobileDirtyword> list = mobileDirtywordMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<DirtyWordVO> voList = new ArrayList<>();
        for (MobileDirtyword md : list) {
            DirtyWordVO vo = new DirtyWordVO();
            vo.setId(md.getId());
            vo.setDirtyword(md.getDirtyword());
            vo.setOwntype(md.getExtend1());
            vo.setCreater(md.getExtend2());
            voList.add(vo);
        }
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            mobileDirtywordMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public DirtyWordVO findById(Long id) {
        MobileDirtyword md = mobileDirtywordMapper.selectByPrimaryKey(id);
        if (md == null) return null;
        DirtyWordVO vo = new DirtyWordVO();
        vo.setId(md.getId());
        vo.setDirtyword(md.getDirtyword());
        vo.setOwntype(md.getExtend1());
        vo.setCreater(md.getExtend2());
        return vo;
    }

    @Override
    public void save(DirtyWordVO dirtyWordVO) {
        MobileDirtyword md = new MobileDirtyword();
        md.setDirtyword(dirtyWordVO.getDirtyword());
        md.setExtend1(dirtyWordVO.getOwntype());
        md.setExtend2(dirtyWordVO.getCreater());
        md.setCreated(new Date());
        mobileDirtywordMapper.insertSelective(md);
    }

    @Override
    public void update(DirtyWordVO dirtyWordVO) {
        MobileDirtyword md = mobileDirtywordMapper.selectByPrimaryKey(dirtyWordVO.getId());
        if (md == null) return;
        md.setDirtyword(dirtyWordVO.getDirtyword());
        md.setExtend1(dirtyWordVO.getOwntype());
        md.setExtend2(dirtyWordVO.getCreater());
        md.setUpdated(new Date());
        mobileDirtywordMapper.updateByPrimaryKeySelective(md);
    }
}
