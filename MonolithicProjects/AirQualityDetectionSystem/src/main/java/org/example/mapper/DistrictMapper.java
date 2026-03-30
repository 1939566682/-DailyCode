package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.vo.DistrictVO;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:29
 */

@Mapper
public interface DistrictMapper {

    /**
     * 查询所有区域信息
     * @return
     */
    @Select("select * from district")
    List<DistrictVO> list();
}
