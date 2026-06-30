package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.GrayRelease;
import org.example.entity.GrayReleaseExample;

public interface GrayReleaseMapper {
    long countByExample(GrayReleaseExample example);
    int deleteByExample(GrayReleaseExample example);
    int deleteByPrimaryKey(Long id);
    int insert(GrayRelease row);
    int insertSelective(GrayRelease row);
    List<GrayRelease> selectByExample(GrayReleaseExample example);
    GrayRelease selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") GrayRelease row, @Param("example") GrayReleaseExample example);
    int updateByExample(@Param("row") GrayRelease row, @Param("example") GrayReleaseExample example);
    int updateByPrimaryKeySelective(GrayRelease row);
    int updateByPrimaryKey(GrayRelease row);
}
