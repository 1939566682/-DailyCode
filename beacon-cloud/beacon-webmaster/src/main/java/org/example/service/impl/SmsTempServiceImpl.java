package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.SmsTemp;
import org.example.entity.SmsTempExample;
import org.example.mapper.SmsTempMapper;
import org.example.service.SmsTempService;
import org.example.util.PageResult;
import org.example.vo.SmsTempVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SmsTempServiceImpl - 短信模板Service实现
 * TODO: 需确认数据库 sms_temp 表是否已创建
 */
@Service
public class SmsTempServiceImpl implements SmsTempService {

    @Autowired
    private SmsTempMapper smsTempMapper;

    @Override
    public PageResult<SmsTempVO> list(int offset, int limit, String search) {
        SmsTempExample example = new SmsTempExample();
        SmsTempExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(search)) {
            criteria.andTemplateLike("%" + search + "%");
        }
        example.setOrderByClause("id asc");

        PageHelper.offsetPage(offset, limit);
        List<SmsTemp> list = smsTempMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();

        List<SmsTempVO> voList = new ArrayList<>();
        for (SmsTemp temp : list) {
            SmsTempVO vo = new SmsTempVO();
            BeanUtils.copyProperties(temp, vo);
            voList.add(vo);
        }

        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            smsTempMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public SmsTempVO findById(Long id) {
        SmsTemp temp = smsTempMapper.selectByPrimaryKey(id);
        if (temp == null) {
            return null;
        }
        SmsTempVO vo = new SmsTempVO();
        BeanUtils.copyProperties(temp, vo);
        return vo;
    }

    @Override
    public void save(SmsTempVO smsTempVO) {
        SmsTemp temp = new SmsTemp();
        BeanUtils.copyProperties(smsTempVO, temp);
        temp.setCreated(new Date());
        temp.setUpdated(new Date());
        if (temp.getStatus() == null) {
            temp.setStatus(1); // 默认启用
        }
        if (temp.getOwntype() == null) {
            temp.setOwntype(1); // 默认管理员
        }
        smsTempMapper.insertSelective(temp);
    }

    @Override
    public void update(SmsTempVO smsTempVO) {
        SmsTemp temp = new SmsTemp();
        BeanUtils.copyProperties(smsTempVO, temp);
        temp.setUpdated(new Date());
        smsTempMapper.updateByPrimaryKeySelective(temp);
    }
}
