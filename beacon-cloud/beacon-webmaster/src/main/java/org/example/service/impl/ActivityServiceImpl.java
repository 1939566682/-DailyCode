package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.Activity;
import org.example.entity.ActivityExample;
import org.example.mapper.ActivityMapper;
import org.example.service.ActivityService;
import org.example.util.PageResult;
import org.example.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public PageResult<ActivityVO> list(int offset, int limit, String search) {
        ActivityExample example = new ActivityExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andtitleLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<Activity> list = activityMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<ActivityVO> voList = new ArrayList<>();
        for (Activity act : list) {
            ActivityVO vo = toVO(act);
            voList.add(vo);
        }
        return new PageResult<>(total, voList);
    }

    private ActivityVO toVO(Activity act) {
        ActivityVO vo = new ActivityVO();
        vo.setId(act.getId());
        vo.setTitle(act.getTitle());
        vo.setAuthor(act.getAuthor());
        if (act.getBeginTime() != null) {
            vo.setBeginTime(sdf.format(act.getBeginTime()));
        }
        if (act.getEndTime() != null) {
            vo.setEndTime(sdf.format(act.getEndTime()));
        }
        vo.setLink(act.getLink());
        vo.setCoverPic(act.getCoverPic());
        return vo;
    }

    private Activity toEntity(ActivityVO vo) {
        Activity act = new Activity();
        act.setId(vo.getId());
        act.setTitle(vo.getTitle());
        act.setAuthor(vo.getAuthor());
        act.setLink(vo.getLink());
        act.setCoverPic(vo.getCoverPic());
        // beginTime/endTime 是 String→Date，需要解析
        if (vo.getBeginTime() != null && !vo.getBeginTime().isEmpty()) {
            try {
                act.setBeginTime(sdf.parse(vo.getBeginTime()));
            } catch (Exception e) {
                act.setBeginTime(new Date());
            }
        }
        if (vo.getEndTime() != null && !vo.getEndTime().isEmpty()) {
            try {
                act.setEndTime(sdf.parse(vo.getEndTime()));
            } catch (Exception e) {
                act.setEndTime(new Date());
            }
        }
        return act;
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            activityMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public ActivityVO findById(Long id) {
        Activity act = activityMapper.selectByPrimaryKey(id);
        if (act == null) return null;
        return toVO(act);
    }

    @Override
    public void save(ActivityVO vo) {
        Activity act = toEntity(vo);
        act.setCreated(new Date());
        activityMapper.insertSelective(act);
    }

    @Override
    public void update(ActivityVO vo) {
        Activity act = toEntity(vo);
        act.setUpdated(new Date());
        activityMapper.updateByPrimaryKeySelective(act);
    }
}
