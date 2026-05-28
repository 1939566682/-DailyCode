package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entity.ClientSign;

import java.util.List;

/**
 * ClientBalanceMapper
 *
 * @author Yang QingBo
 * @date 2026-05-28 20:28
 * @description
 */

@Mapper
public interface ClientBalanceMapper {
	
	@Select("select balance from client_balance where client_id = #{clientId}")
	Long findByClientId(@Param("clientId")Long clientId);
	
}
