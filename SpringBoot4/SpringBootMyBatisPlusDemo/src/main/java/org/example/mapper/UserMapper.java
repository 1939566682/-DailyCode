package org.example.mapper;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-29 19:39
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.pojo.User;

/**
 * 继承BaseMapper接口，该接口中定义了很多基础的增删改查的方法，
        * 所以UserMapper中就不需要定义基础的增删改查方法了
 */
public interface UserMapper extends BaseMapper<User> {

}