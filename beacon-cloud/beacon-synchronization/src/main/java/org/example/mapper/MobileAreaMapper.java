package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entity.ClientTemplate;
import org.example.entity.MobileArea;

import java.util.List;

/**
 * ClientTemplateMapper
 *
 * @author Yang QingBo
 * @date 2026-05-28 19:43
 * @description
 */

@Mapper
public interface MobileAreaMapper {
	
	@Select("select mobile_number,mobile_area,mobile_type from mobile_area")
	List<MobileArea> findAll();

}
