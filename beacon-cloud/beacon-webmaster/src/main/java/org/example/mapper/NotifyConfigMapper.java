package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.NotifyConfig;
import org.example.entity.NotifyConfigExample;

public interface NotifyConfigMapper {
    long countByExample(NotifyConfigExample example);
    int deleteByExample(NotifyConfigExample example);
    int deleteByPrimaryKey(Long id);
    int insert(NotifyConfig row);
    int insertSelective(NotifyConfig row);
    List<NotifyConfig> selectByExample(NotifyConfigExample example);
    NotifyConfig selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") NotifyConfig row, @Param("example") NotifyConfigExample example);
    int updateByExample(@Param("row") NotifyConfig row, @Param("example") NotifyConfigExample example);
    int updateByPrimaryKeySelective(NotifyConfig row);
    int updateByPrimaryKey(NotifyConfig row);
}
