package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entity.ClientBusiness;

/**
 * ClientBusinessMapper
 *
 * @author Yang QingBo
 * @date 2026-05-26 18:21
 * @description
 */

@Mapper
public interface ClientBusinessMapper {

	@Select("select * from client_business where id = #{id}")
	ClientBusiness findById(@Param("id") Long id);

}
