package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.ApiMapping;
import org.example.entity.ApiMappingExample;

public interface ApiMappingMapper {
    long countByExample(ApiMappingExample example);
    int deleteByExample(ApiMappingExample example);
    int deleteByPrimaryKey(Long id);
    int insert(ApiMapping row);
    int insertSelective(ApiMapping row);
    List<ApiMapping> selectByExample(ApiMappingExample example);
    ApiMapping selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") ApiMapping row, @Param("example") ApiMappingExample example);
    int updateByExample(@Param("row") ApiMapping row, @Param("example") ApiMappingExample example);
    int updateByPrimaryKeySelective(ApiMapping row);
    int updateByPrimaryKey(ApiMapping row);
}
