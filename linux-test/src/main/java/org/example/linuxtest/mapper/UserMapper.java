package org.example.linuxtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

/**
 * UserMapper
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-06 19:46
 */

@Mapper
public interface UserMapper {
	
	// 查询所有用户
	@Select("select name from user")
	List<String> selectAllUser();
}
