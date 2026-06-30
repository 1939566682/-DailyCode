package org.example.service;

import org.example.util.PageResult;
import org.example.vo.SearchParamsVO;
import java.util.List;

public interface SearchParamsService {
    PageResult<SearchParamsVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    SearchParamsVO findById(Long id);
    void save(SearchParamsVO vo);
    void update(SearchParamsVO vo);
}
