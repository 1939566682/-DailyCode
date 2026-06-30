package org.example.service;

import org.example.util.PageResult;
import org.example.vo.DirtyWordVO;
import java.util.List;

public interface DirtyWordService {
    PageResult<DirtyWordVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    DirtyWordVO findById(Long id);
    void save(DirtyWordVO dirtyWordVO);
    void update(DirtyWordVO dirtyWordVO);
}
