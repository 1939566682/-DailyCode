package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entity.ClientTemplate;

import java.util.List;

/**
 * ClientTemplateMapper
 *
 * @author Yang QingBo
 * @date 2026-05-28 19:43
 * @description
 */

@Mapper
public interface ClientTemplateMapper {
	
	@Select("select * from client_template where sign_id = #{signId}")
	List<ClientTemplate> findBySignId(@Param("signId") Long signId);

}
