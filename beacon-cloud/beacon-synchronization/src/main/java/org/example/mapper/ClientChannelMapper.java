package org.example.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.ClientChannel;

import java.util.List;

/**
 * ClientChannelMapper
 *
 * @author Yang QingBo
 * @date 2026-06-05 16:37
 * @description
 */

@Mapper
public interface ClientChannelMapper {
	
	@Select("select client_id, channel_id,client_channel_weight,client_channel_number,is_available from client_channel where is_delete = 0")
	List<ClientChannel> findAll();
	
}
