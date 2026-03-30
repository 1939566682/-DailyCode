package org.example.service;

import org.example.vo.DistrictVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:41
 */

public interface DistrictService {

    /**
     * 查询所有区域信息
     */
    List<DistrictVO> getDistrictList();
}
