package org.example.hjycommunity.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.hjycommunity.system.domain.pojo.SysArea;

import java.util.List;

/**
 * SysAreaMapper
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 21:39
 */

@Mapper
public interface SysAreaMapper {

    /**
     * 查询所有区域信息
     * @return
     */
    List<SysArea> findAll();

}
