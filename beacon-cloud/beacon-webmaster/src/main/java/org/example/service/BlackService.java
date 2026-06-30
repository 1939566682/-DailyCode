package org.example.service;

import org.example.util.PageResult;
import org.example.vo.BlackVO;
import java.util.List;

public interface BlackService {
    PageResult<BlackVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    BlackVO findById(Long id);
    void save(BlackVO blackVO);
    void update(BlackVO blackVO);
}
