package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.NotifyConfig;
import org.example.entity.NotifyConfigExample;
import org.example.mapper.NotifyConfigMapper;
import org.example.service.NotifyService;
import org.example.util.PageResult;
import org.example.util.VOConverter;
import org.example.vo.NotifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private NotifyConfigMapper notifyConfigMapper;

    @Override
    public PageResult<NotifyVO> list(int offset, int limit, String search) {
        NotifyConfigExample example = new NotifyConfigExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andtagLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<NotifyConfig> list = notifyConfigMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<NotifyVO> voList = VOConverter.toVOList(list, NotifyVO.class);
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            notifyConfigMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public NotifyVO findById(Long id) {
        NotifyConfig entity = notifyConfigMapper.selectByPrimaryKey(id);
        return VOConverter.toVO(entity, NotifyVO.class);
    }

    @Override
    public void save(NotifyVO vo) {
        NotifyConfig entity = VOConverter.toEntity(vo, NotifyConfig.class);
        entity.setCreated(new Date());
        notifyConfigMapper.insertSelective(entity);
    }

    @Override
    public void update(NotifyVO vo) {
        NotifyConfig entity = VOConverter.toEntity(vo, NotifyConfig.class);
        entity.setUpdated(new Date());
        notifyConfigMapper.updateByPrimaryKeySelective(entity);
    }
}
