package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Channel;
import org.example.entity.ChannelExample;

public interface ChannelMapper {
    long countByExample(ChannelExample example);
    int deleteByExample(ChannelExample example);
    int deleteByPrimaryKey(Long id);
    int insert(Channel row);
    int insertSelective(Channel row);
    List<Channel> selectByExample(ChannelExample example);
    Channel selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("row") Channel row, @Param("example") ChannelExample example);
    int updateByExample(@Param("row") Channel row, @Param("example") ChannelExample example);
    int updateByPrimaryKeySelective(Channel row);
    int updateByPrimaryKey(Channel row);
}
