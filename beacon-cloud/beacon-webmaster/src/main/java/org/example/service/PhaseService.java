package org.example.service;

import org.example.util.PageResult;
import org.example.vo.PhaseVO;
import java.util.List;

public interface PhaseService {
    PageResult<PhaseVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    PhaseVO findById(Long id);
    void save(PhaseVO vo);
    void update(PhaseVO vo);
    List<PhaseVO> findAllProvs();
    List<PhaseVO> findCitysByProvId(Long provId);
}
