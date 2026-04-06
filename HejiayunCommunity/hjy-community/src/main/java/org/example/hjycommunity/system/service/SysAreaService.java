package org.example.hjycommunity.system.service;

import org.example.hjycommunity.system.domain.dto.SysAreaDTO;

import java.util.List;

/**
 * SysAreaService
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 21:45
 */

public interface SysAreaService {

    /**
     * 获取区域数据完整树
     * @return
     */
    List<SysAreaDTO> findAreaAsTree();
}
