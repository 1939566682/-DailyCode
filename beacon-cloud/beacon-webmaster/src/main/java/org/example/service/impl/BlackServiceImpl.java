package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.ClientBusiness;
import org.example.entity.MobileBlack;
import org.example.entity.MobileBlackExample;
import org.example.mapper.ClientBusinessMapper;
import org.example.mapper.MobileBlackMapper;
import org.example.service.BlackService;
import org.example.util.PageResult;
import org.example.vo.BlackVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlackServiceImpl implements BlackService {
    @Autowired
    private MobileBlackMapper mobileBlackMapper;
    @Autowired
    private ClientBusinessMapper clientBusinessMapper;

    @Override
    public PageResult<BlackVO> list(int offset, int limit, String search) {
        MobileBlackExample example = new MobileBlackExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andblackNumberLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<MobileBlack> list = mobileBlackMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<BlackVO> voList = new ArrayList<>();
        for (MobileBlack mb : list) {
            BlackVO vo = new BlackVO();
            vo.setId(mb.getId());
            vo.setMobile(mb.getBlackNumber());
            vo.setClientId(mb.getClientId());
            // owntype: clientId=0映射为"全局"，否则联查client_business的corpname
            if (mb.getClientId() != null && mb.getClientId() == 0) {
                vo.setOwntype("全局");
            } else if (mb.getClientId() != null) {
                ClientBusiness cb = clientBusinessMapper.selectByPrimaryKey(Long.valueOf(mb.getClientId()));
                if (cb != null) {
                    vo.setOwntype(cb.getCorpname());
                }
            }
            // creater: 联查
            if (mb.getCreateId() != null) {
                ClientBusiness cb = clientBusinessMapper.selectByPrimaryKey(mb.getCreateId());
                if (cb != null) {
                    vo.setCreater(cb.getCorpname());
                }
            }
            voList.add(vo);
        }
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            mobileBlackMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public BlackVO findById(Long id) {
        MobileBlack mb = mobileBlackMapper.selectByPrimaryKey(id);
        if (mb == null) return null;
        BlackVO vo = new BlackVO();
        vo.setId(mb.getId());
        vo.setMobile(mb.getBlackNumber());
        vo.setClientId(mb.getClientId());
        if (mb.getClientId() != null && mb.getClientId() == 0) {
            vo.setOwntype("全局");
        } else if (mb.getClientId() != null) {
            ClientBusiness cb = clientBusinessMapper.selectByPrimaryKey(Long.valueOf(mb.getClientId()));
            if (cb != null) {
                vo.setOwntype(cb.getCorpname());
            }
        }
        if (mb.getCreateId() != null) {
            ClientBusiness cb = clientBusinessMapper.selectByPrimaryKey(mb.getCreateId());
            if (cb != null) {
                vo.setCreater(cb.getCorpname());
            }
        }
        return vo;
    }

    @Override
    public void save(BlackVO blackVO) {
        MobileBlack mb = new MobileBlack();
        mb.setBlackNumber(blackVO.getMobile());
        mb.setClientId(blackVO.getClientId());
        mb.setCreated(new Date());
        mobileBlackMapper.insertSelective(mb);
    }

    @Override
    public void update(BlackVO blackVO) {
        MobileBlack mb = mobileBlackMapper.selectByPrimaryKey(blackVO.getId());
        if (mb == null) return;
        mb.setBlackNumber(blackVO.getMobile());
        mb.setClientId(blackVO.getClientId());
        mb.setUpdated(new Date());
        mobileBlackMapper.updateByPrimaryKeySelective(mb);
    }
}
