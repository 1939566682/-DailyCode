package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.MobileArea;
import org.example.entity.MobileAreaExample;

public interface MobileAreaMapper {
    long countByExample(MobileAreaExample example);
    int deleteByExample(MobileAreaExample example);
    int deleteByPrimaryKey(Long id);
    int insert(MobileArea row);
    int insertSelective(MobileArea row);
    List<MobileArea> selectByExample(MobileAreaExample example);
    MobileArea selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") MobileArea row, @Param("example") MobileAreaExample example);
    int updateByExample(@Param("row") MobileArea row, @Param("example") MobileAreaExample example);
    int updateByPrimaryKeySelective(MobileArea row);
    int updateByPrimaryKey(MobileArea row);
}
