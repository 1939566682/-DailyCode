package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 17:57
 */

public interface UserMapper {
    User selectOneUser(@Param("uname") String uname, @Param("pwd") String pwd);
}