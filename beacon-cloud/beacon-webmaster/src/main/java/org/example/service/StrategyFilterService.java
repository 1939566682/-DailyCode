package org.example.service;

import org.example.util.PageResult;
import org.example.vo.StrategyFilterVO;
import java.util.List;

public interface StrategyFilterService {
    PageResult<StrategyFilterVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    StrategyFilterVO findById(Long id);
    void save(StrategyFilterVO vo);
    void update(StrategyFilterVO vo);
}
