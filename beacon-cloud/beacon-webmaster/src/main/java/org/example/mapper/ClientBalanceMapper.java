package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.ClientBalance;
import org.example.entity.ClientBalanceExample;

public interface ClientBalanceMapper {
    long countByExample(ClientBalanceExample example);
    int deleteByExample(ClientBalanceExample example);
    int deleteByPrimaryKey(Long id);
    int insert(ClientBalance row);
    int insertSelective(ClientBalance row);
    List<ClientBalance> selectByExample(ClientBalanceExample example);
    ClientBalance selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") ClientBalance row, @Param("example") ClientBalanceExample example);
    int updateByExample(@Param("row") ClientBalance row, @Param("example") ClientBalanceExample example);
    int updateByPrimaryKeySelective(ClientBalance row);
    int updateByPrimaryKey(ClientBalance row);
}
