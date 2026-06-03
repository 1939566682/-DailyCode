package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.MobileTransfer;

import java.util.List;

/**
 * ClientTemplateMapper
 *
 * @author Yang QingBo
 * @date 2026-05-28 19:43
 * @description
 */

@Mapper
public interface MobileTransferMapper {
	
	@Select("select transfer_number,now_isp from mobile_transfer where is_delete = 0 and is_transfer = 1")
	List<MobileTransfer> findAll();

}
