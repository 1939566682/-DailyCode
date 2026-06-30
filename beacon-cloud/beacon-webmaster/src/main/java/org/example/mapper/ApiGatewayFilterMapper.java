package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.ApiGatewayFilter;
import org.example.entity.ApiGatewayFilterExample;

public interface ApiGatewayFilterMapper {
    long countByExample(ApiGatewayFilterExample example);
    int deleteByExample(ApiGatewayFilterExample example);
    int deleteByPrimaryKey(Long id);
    int insert(ApiGatewayFilter row);
    int insertSelective(ApiGatewayFilter row);
    List<ApiGatewayFilter> selectByExample(ApiGatewayFilterExample example);
    ApiGatewayFilter selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") ApiGatewayFilter row, @Param("example") ApiGatewayFilterExample example);
    int updateByExample(@Param("row") ApiGatewayFilter row, @Param("example") ApiGatewayFilterExample example);
    int updateByPrimaryKeySelective(ApiGatewayFilter row);
    int updateByPrimaryKey(ApiGatewayFilter row);
}
