package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.ClientBusiness;
import org.example.entity.ClientBusinessExample;

public interface ClientBusinessMapper {
    long countByExample(ClientBusinessExample example);

    int deleteByExample(ClientBusinessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClientBusiness row);

    int insertSelective(ClientBusiness row);

    List<ClientBusiness> selectByExample(ClientBusinessExample example);

    ClientBusiness selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ClientBusiness row, @Param("example") ClientBusinessExample example);

    int updateByExample(@Param("row") ClientBusiness row, @Param("example") ClientBusinessExample example);

    int updateByPrimaryKeySelective(ClientBusiness row);

    int updateByPrimaryKey(ClientBusiness row);
}