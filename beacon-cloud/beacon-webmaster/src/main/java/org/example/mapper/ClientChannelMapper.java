package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.ClientChannel;
import org.example.entity.ClientChannelExample;

public interface ClientChannelMapper {
    long countByExample(ClientChannelExample example);
    int deleteByExample(ClientChannelExample example);
    int deleteByPrimaryKey(Long id);
    int insert(ClientChannel row);
    int insertSelective(ClientChannel row);
    List<ClientChannel> selectByExample(ClientChannelExample example);
    ClientChannel selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") ClientChannel row, @Param("example") ClientChannelExample example);
    int updateByExample(@Param("row") ClientChannel row, @Param("example") ClientChannelExample example);
    int updateByPrimaryKeySelective(ClientChannel row);
    int updateByPrimaryKey(ClientChannel row);
}
