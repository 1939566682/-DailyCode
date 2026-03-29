package org.example.service;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-29 19:40
 */

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.User;

/**
 * 继承IService接口，该接口中定义了很多基础的增删改查的方法，
 * 以UserService中就不需要定义基础的增删改查方法了
 */
public interface UserService extends IService<User> {
}