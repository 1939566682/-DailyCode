package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entity.ClientSign;

import java.util.List;

/**
 * ClientSignMapper
 *
 * @author Yang QingBo
 * @date 2026-05-28 18:04
 * @description
 */

@Mapper
public interface ClientSignMapper {
	
	@Select("select * from client_sign where client_id = #{clientId}")
	List<ClientSign> findByClientId(@Param("clientId") Long clientId);
}
