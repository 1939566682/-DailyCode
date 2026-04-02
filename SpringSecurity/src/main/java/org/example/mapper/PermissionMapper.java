package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-02 15:15
 */

@Mapper
public interface PermissionMapper {

    Set<String> findPermissionByUserId(@Param("id") Long userId);

}
