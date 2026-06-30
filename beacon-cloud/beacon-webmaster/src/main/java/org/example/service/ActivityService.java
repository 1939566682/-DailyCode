package org.example.service;

import org.example.util.PageResult;
import org.example.vo.ActivityVO;
import java.util.List;

public interface ActivityService {
    PageResult<ActivityVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    ActivityVO findById(Long id);
    void save(ActivityVO vo);
    void update(ActivityVO vo);
}
