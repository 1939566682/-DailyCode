package org.example.service;

import org.example.util.PageResult;
import org.example.vo.GrayReleaseVO;
import java.util.List;

public interface GrayReleaseService {
    PageResult<GrayReleaseVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    GrayReleaseVO findById(Long id);
    void save(GrayReleaseVO vo);
    void update(GrayReleaseVO vo);
}
