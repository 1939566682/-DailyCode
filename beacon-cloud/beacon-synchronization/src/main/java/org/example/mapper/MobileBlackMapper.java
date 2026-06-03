package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.MobileArea;
import org.example.entity.MobileBlack;

import java.util.List;

/**
 * MobileBlackMapper
 *
 * @author Yang QingBo
 * @date 2026-05-28 19:43
 * @description
 */

@Mapper
public interface MobileBlackMapper {
	
	@Select("select black_number,client_id from mobile_black where is_delete =0")
	List<MobileBlack> findAll();

}
