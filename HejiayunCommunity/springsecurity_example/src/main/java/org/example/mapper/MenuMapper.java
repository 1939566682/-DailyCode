package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Menu;

import java.util.List;

/**
 * MenuMapper
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-10 15:22
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    // 根据用户 id 获取用户权限信息
    List<String> selectPermsByUserId(@Param("userId") Long userId);
}
