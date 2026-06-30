package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.MobileBlack;
import org.example.entity.MobileBlackExample;

public interface MobileBlackMapper {
    long countByExample(MobileBlackExample example);
    int deleteByExample(MobileBlackExample example);
    int deleteByPrimaryKey(Long id);
    int insert(MobileBlack row);
    int insertSelective(MobileBlack row);
    List<MobileBlack> selectByExample(MobileBlackExample example);
    MobileBlack selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") MobileBlack row, @Param("example") MobileBlackExample example);
    int updateByExample(@Param("row") MobileBlack row, @Param("example") MobileBlackExample example);
    int updateByPrimaryKeySelective(MobileBlack row);
    int updateByPrimaryKey(MobileBlack row);
}
