package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.StrategyFilter;
import org.example.entity.StrategyFilterExample;

public interface StrategyFilterMapper {
    long countByExample(StrategyFilterExample example);
    int deleteByExample(StrategyFilterExample example);
    int deleteByPrimaryKey(Long id);
    int insert(StrategyFilter row);
    int insertSelective(StrategyFilter row);
    List<StrategyFilter> selectByExample(StrategyFilterExample example);
    StrategyFilter selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") StrategyFilter row, @Param("example") StrategyFilterExample example);
    int updateByExample(@Param("row") StrategyFilter row, @Param("example") StrategyFilterExample example);
    int updateByPrimaryKeySelective(StrategyFilter row);
    int updateByPrimaryKey(StrategyFilter row);
}
