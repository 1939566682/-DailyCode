package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
public interface MobileDirtyWordMapper {
	
	@Select("select dirtyword from mobile_dirtyword")
	List<String> findDirtyWord();

}
