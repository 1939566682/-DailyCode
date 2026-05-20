package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * UserPointsIdempotent
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-20 13:58
 */

@Mapper
public interface UserPointsIdempotentMapper {

	@Select("select count(1) from user_points_idempotent where id = #{id}")
	Integer findById(@Param("id") String id);
	
	@Insert("insert into user_points_idempotent (id) values (#{id}) ")
	void save(@Param("id") String id);
	
}
