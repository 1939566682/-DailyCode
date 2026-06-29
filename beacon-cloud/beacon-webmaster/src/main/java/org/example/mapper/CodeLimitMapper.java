package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.CodeLimit;
import org.example.entity.CodeLimitExample;

public interface CodeLimitMapper {
    long countByExample(CodeLimitExample example);
    int deleteByExample(CodeLimitExample example);
    int deleteByPrimaryKey(Long id);
    int insert(CodeLimit row);
    int insertSelective(CodeLimit row);
    List<CodeLimit> selectByExample(CodeLimitExample example);
    CodeLimit selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") CodeLimit row, @Param("example") CodeLimitExample example);
    int updateByExample(@Param("row") CodeLimit row, @Param("example") CodeLimitExample example);
    int updateByPrimaryKeySelective(CodeLimit row);
    int updateByPrimaryKey(CodeLimit row);
}
