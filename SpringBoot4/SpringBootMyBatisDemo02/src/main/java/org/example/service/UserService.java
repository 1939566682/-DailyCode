package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.pojo.User;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 19:04
 */

public interface UserService {
    User findOneUser(Integer id);

    int save(User user);

    int update(User user);

    int deleteUserById(Integer id);

    /**
     *
     * @param pageNum 页码
     * @param pageSize 每页数据大小
     * @return
     */
    PageInfo<User> findAllUsers(int pageNum, int pageSize);
}
