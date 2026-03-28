package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.User;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 18:58
 */

@Mapper
public interface UserMapper {
    // 查询：查询一条用户数据：
    User selectOneUser(Integer id);
    // 保存：保存一条用户信息：
    int save(User user);
    // 更新：更新一条用户信息：
    int update(User user);
    // 删除：根据id删除一条用户记录：
    int delete(Integer id);

    List<User> selectAllUsers();
}