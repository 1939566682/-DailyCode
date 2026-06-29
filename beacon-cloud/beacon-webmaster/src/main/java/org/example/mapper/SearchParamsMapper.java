package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.SearchParams;
import org.example.entity.SearchParamsExample;

public interface SearchParamsMapper {
    long countByExample(SearchParamsExample example);
    int deleteByExample(SearchParamsExample example);
    int deleteByPrimaryKey(Long id);
    int insert(SearchParams row);
    int insertSelective(SearchParams row);
    List<SearchParams> selectByExample(SearchParamsExample example);
    SearchParams selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") SearchParams row, @Param("example") SearchParamsExample example);
    int updateByExample(@Param("row") SearchParams row, @Param("example") SearchParamsExample example);
    int updateByPrimaryKeySelective(SearchParams row);
    int updateByPrimaryKey(SearchParams row);
}
