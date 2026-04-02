package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

import java.util.Set;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 20:16
 */

@Mapper
public interface UserMapper {

    @Select("select * from `user` where username = #{username} ")
    User findUserByUsername(@Param("username") String username);


}
