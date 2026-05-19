package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * ResendMapper
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 20:17
 */

@Mapper
public interface ResendMapper {

	@Insert("insert into resend (id, message, exchange, routing_key, send_time) values (#{id},#{message},#{exchange},#{routingKey},#{sendTime})")
	void save(Map map);
	
}
