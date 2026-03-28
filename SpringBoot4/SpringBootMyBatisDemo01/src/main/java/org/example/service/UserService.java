package org.example.service;

import org.example.pojo.User;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 16:29
 */

public interface UserService {
    List<User> findAllUsers();
}