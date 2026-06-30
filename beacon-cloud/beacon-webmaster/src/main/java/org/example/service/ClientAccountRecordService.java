package org.example.service;

import org.example.util.PageResult;
import org.example.vo.AcountVO;

public interface ClientAccountRecordService {
    PageResult<AcountVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    AcountVO findById(Long id);
    void save(AcountVO vo);
    void update(AcountVO vo);
}
