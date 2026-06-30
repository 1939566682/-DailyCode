package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.ClientAccountRecord;
import org.example.entity.ClientAccountRecordExample;

public interface ClientAccountRecordMapper {
    long countByExample(ClientAccountRecordExample example);
    int deleteByExample(ClientAccountRecordExample example);
    int deleteByPrimaryKey(Long id);
    int insert(ClientAccountRecord row);
    int insertSelective(ClientAccountRecord row);
    List<ClientAccountRecord> selectByExample(ClientAccountRecordExample example);
    ClientAccountRecord selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") ClientAccountRecord row, @Param("example") ClientAccountRecordExample example);
    int updateByExample(@Param("row") ClientAccountRecord row, @Param("example") ClientAccountRecordExample example);
    int updateByPrimaryKeySelective(ClientAccountRecord row);
    int updateByPrimaryKey(ClientAccountRecord row);
}
