package org.example.mapper;

import org.apache.ibatis.annotations.*;

/**
 * TBOrder
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-20 15:16
 */

@Mapper
public interface TBOrderMapper {

	@Insert("insert into tb_order (id) values (#{id})")
	void save (@Param("id") String id);
	
	@Select("select order_state from tb_order where id = #{id} for update")
	int findOrderStateByIdUpdate(@Param("id") String id);
	
	@Update("update tb_order set order_state = #{orderState} where id = #{id}")
	void updateOrderStateById(@Param("orderState")int i, @Param("id") String id);
}
