package org.example.service;

import org.example.util.PageResult;
import org.example.vo.NotifyVO;
import java.util.List;

public interface NotifyService {
    PageResult<NotifyVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    NotifyVO findById(Long id);
    void save(NotifyVO vo);
    void update(NotifyVO vo);
}
