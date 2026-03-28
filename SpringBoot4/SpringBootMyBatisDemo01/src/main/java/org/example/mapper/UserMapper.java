package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 16:28
 */

//@Mapper
public interface UserMapper {
    /*查询*/
//    @Select("select * from t_user")
    List<User> selectAllUsers();
}