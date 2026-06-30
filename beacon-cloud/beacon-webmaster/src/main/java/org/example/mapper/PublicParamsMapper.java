package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.PublicParams;
import org.example.entity.PublicParamsExample;

public interface PublicParamsMapper {
    long countByExample(PublicParamsExample example);
    int deleteByExample(PublicParamsExample example);
    int deleteByPrimaryKey(Long id);
    int insert(PublicParams row);
    int insertSelective(PublicParams row);
    List<PublicParams> selectByExample(PublicParamsExample example);
    PublicParams selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") PublicParams row, @Param("example") PublicParamsExample example);
    int updateByExample(@Param("row") PublicParams row, @Param("example") PublicParamsExample example);
    int updateByPrimaryKeySelective(PublicParams row);
    int updateByPrimaryKey(PublicParams row);
}
