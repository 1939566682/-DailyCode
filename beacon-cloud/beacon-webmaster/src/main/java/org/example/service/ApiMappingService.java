package org.example.service;

import org.example.util.PageResult;
import org.example.vo.ApiMappingVO;
import java.util.List;

public interface ApiMappingService {
    PageResult<ApiMappingVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    ApiMappingVO findById(Long id);
    void save(ApiMappingVO vo);
    void update(ApiMappingVO vo);
}
