package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Channel;
import org.example.entity.ClientBalance;

import java.util.List;

/**
 * ChannelMapper
 *
 * @author Yang QingBo
 * @date 2026-06-05 16:19
 * @description
 */

@Mapper
public interface ChannelMapper {
	
	@Select("select * from channel where is_delete = 0")
	List<Channel> findAll();

}
