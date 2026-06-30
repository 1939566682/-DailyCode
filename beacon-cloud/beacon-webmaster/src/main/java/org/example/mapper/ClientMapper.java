package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Client;
import org.example.entity.ClientExample;

public interface ClientMapper {
    long countByExample(ClientExample example);
    int deleteByExample(ClientExample example);
    int deleteByPrimaryKey(Long id);
    int insert(Client row);
    int insertSelective(Client row);
    List<Client> selectByExample(ClientExample example);
    Client selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") Client row, @Param("example") ClientExample example);
    int updateByExample(@Param("row") Client row, @Param("example") ClientExample example);
    int updateByPrimaryKeySelective(Client row);
    int updateByPrimaryKey(Client row);
}
