package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.MobileArea;
import org.example.entity.MobileAreaExample;
import org.example.entity.SmsPhase;
import org.example.entity.SmsPhaseExample;
import org.example.mapper.MobileAreaMapper;
import org.example.mapper.SmsPhaseMapper;
import org.example.service.PhaseService;
import org.example.util.PageResult;
import org.example.util.VOConverter;
import org.example.vo.PhaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PhaseServiceImpl implements PhaseService {
    @Autowired
    private SmsPhaseMapper smsPhaseMapper;
    @Autowired
    private MobileAreaMapper mobileAreaMapper;

    @Override
    public PageResult<PhaseVO> list(int offset, int limit, String search) {
        SmsPhaseExample example = new SmsPhaseExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andphaseLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<SmsPhase> list = smsPhaseMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<PhaseVO> voList = VOConverter.toVOList(list, PhaseVO.class);
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            smsPhaseMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PhaseVO findById(Long id) {
        SmsPhase entity = smsPhaseMapper.selectByPrimaryKey(id);
        return VOConverter.toVO(entity, PhaseVO.class);
    }

    @Override
    public void save(PhaseVO vo) {
        SmsPhase entity = VOConverter.toEntity(vo, SmsPhase.class);
        entity.setCreated(new Date());
        smsPhaseMapper.insertSelective(entity);
    }

    @Override
    public void update(PhaseVO vo) {
        SmsPhase entity = VOConverter.toEntity(vo, SmsPhase.class);
        entity.setUpdated(new Date());
        smsPhaseMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<PhaseVO> findAllProvs() {
        MobileAreaExample example = new MobileAreaExample();
        example.setDistinct(true);
        // 查询所有省份（去重）
        List<MobileArea> areas = mobileAreaMapper.selectByExample(example);
        List<PhaseVO> provs = new ArrayList<>();
        for (MobileArea area : areas) {
            PhaseVO vo = new PhaseVO();
            vo.setProvName(area.getProvince());
            vo.setProvId(Long.valueOf(area.getProvinceCode() != null ? area.getProvinceCode().hashCode() : 0));
            provs.add(vo);
        }
        return provs;
    }

    @Override
    public List<PhaseVO> findCitysByProvId(Long provId) {
        // 通过省份编码查询城市列表
        MobileAreaExample example = new MobileAreaExample();
        List<MobileArea> areas = mobileAreaMapper.selectByExample(example);
        List<PhaseVO> citys = new ArrayList<>();
        for (MobileArea area : areas) {
            PhaseVO vo = new PhaseVO();
            vo.setCityName(area.getCity());
            vo.setCityId(Long.valueOf(area.getCityCode() != null ? area.getCityCode().hashCode() : 0));
            vo.setProvId(provId);
            citys.add(vo);
        }
        return citys;
    }
}
