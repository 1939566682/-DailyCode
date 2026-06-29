package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.SmsPhase;
import org.example.entity.SmsPhaseExample;

public interface SmsPhaseMapper {
    long countByExample(SmsPhaseExample example);
    int deleteByExample(SmsPhaseExample example);
    int deleteByPrimaryKey(Long id);
    int insert(SmsPhase row);
    int insertSelective(SmsPhase row);
    List<SmsPhase> selectByExample(SmsPhaseExample example);
    SmsPhase selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") SmsPhase row, @Param("example") SmsPhaseExample example);
    int updateByExample(@Param("row") SmsPhase row, @Param("example") SmsPhaseExample example);
    int updateByPrimaryKeySelective(SmsPhase row);
    int updateByPrimaryKey(SmsPhase row);
}
