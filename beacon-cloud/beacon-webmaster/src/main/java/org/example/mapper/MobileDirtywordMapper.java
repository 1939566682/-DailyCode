package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.MobileDirtyword;
import org.example.entity.MobileDirtywordExample;

public interface MobileDirtywordMapper {
    long countByExample(MobileDirtywordExample example);
    int deleteByExample(MobileDirtywordExample example);
    int deleteByPrimaryKey(Long id);
    int insert(MobileDirtyword row);
    int insertSelective(MobileDirtyword row);
    List<MobileDirtyword> selectByExample(MobileDirtywordExample example);
    MobileDirtyword selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") MobileDirtyword row, @Param("example") MobileDirtywordExample example);
    int updateByExample(@Param("row") MobileDirtyword row, @Param("example") MobileDirtywordExample example);
    int updateByPrimaryKeySelective(MobileDirtyword row);
    int updateByPrimaryKey(MobileDirtyword row);
}
