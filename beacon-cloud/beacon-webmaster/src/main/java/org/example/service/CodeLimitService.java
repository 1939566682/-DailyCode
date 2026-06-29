package org.example.service;

import org.example.util.PageResult;
import org.example.vo.CodeLimitVO;

public interface CodeLimitService {
    PageResult<CodeLimitVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    CodeLimitVO findById(Long id);
    void save(CodeLimitVO vo);
    void update(CodeLimitVO vo);
}
