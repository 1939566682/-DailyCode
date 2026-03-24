package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 18:20
 */

public interface UserService {
    User selectOneUser(String uname, String pwd);
}