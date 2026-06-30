package org.example.service;

import org.example.util.PageResult;
import org.example.vo.PublicParamsVO;
import java.util.List;

public interface PublicParamsService {
    PageResult<PublicParamsVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    PublicParamsVO findById(Long id);
    void save(PublicParamsVO vo);
    void update(PublicParamsVO vo);
}
