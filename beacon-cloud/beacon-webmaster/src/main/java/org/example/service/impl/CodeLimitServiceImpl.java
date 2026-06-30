package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.CodeLimit;
import org.example.entity.CodeLimitExample;
import org.example.mapper.CodeLimitMapper;
import org.example.service.CodeLimitService;
import org.example.util.PageResult;
import org.example.vo.CodeLimitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * CodeLimitServiceImpl - 验证码限流 Service 实现
 * 路径前缀: /sys/limit
 * 对应前端 limit.js
 */
@Service
public class CodeLimitServiceImpl implements CodeLimitService {

    @Autowired
    private CodeLimitMapper codeLimitMapper;

    @Override
    public PageResult<CodeLimitVO> list(int offset, int limit, String search) {
        CodeLimitExample example = new CodeLimitExample();
        CodeLimitExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(search)) {
            criteria.anddescriptionLike("%" + search + "%");
        }
        example.setOrderByClause("id asc");

        PageHelper.offsetPage(offset, limit);
        List<CodeLimit> list = codeLimitMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();

        List<CodeLimitVO> voList = new ArrayList<>();
        for (CodeLimit entity : list) {
            CodeLimitVO vo = new CodeLimitVO();
            BeanUtils.copyProperties(entity, vo);
            // code_limit 表中无 limit_state 列，这里默认 1（启用）
            vo.setLimitState(1);
            voList.add(vo);
        }

        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            codeLimitMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public CodeLimitVO findById(Long id) {
        CodeLimit entity = codeLimitMapper.selectByPrimaryKey(id);
        if (entity == null) {
            return null;
        }
        CodeLimitVO vo = new CodeLimitVO();
        BeanUtils.copyProperties(entity, vo);
        vo.setLimitState(1);
        return vo;
    }

    @Override
    public void save(CodeLimitVO codeLimitVO) {
        CodeLimit entity = new CodeLimit();
        BeanUtils.copyProperties(codeLimitVO, entity);
        // despcription(VO) → description(Entity) 字段名不同，BeanUtils 不会复制，需手动赋值
        entity.setDescription(codeLimitVO.getDespcription());
        codeLimitMapper.insertSelective(entity);
    }

    @Override
    public void update(CodeLimitVO codeLimitVO) {
        CodeLimit entity = new CodeLimit();
        BeanUtils.copyProperties(codeLimitVO, entity);
        // 同上：despcription → description
        entity.setDescription(codeLimitVO.getDespcription());
        codeLimitMapper.updateByPrimaryKeySelective(entity);
    }
}
