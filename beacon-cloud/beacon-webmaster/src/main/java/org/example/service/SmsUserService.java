package org.example.service;

import org.example.entity.SmsUser;
import org.example.util.PageResult;

import java.util.Map;

/**
 * SmsUserService
 *
 * @author Yang QingBo
 * @date 2026-06-15 15:20
 * @description 用户信息的Service
 */

public interface SmsUserService {

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	SmsUser findByUsername(String username);

	/**
	 * 分页查询用户列表
	 */
	PageResult<Map<String, Object>> list(int offset, int limit, String search);

	/**
	 * 根据ID查询用户
	 */
	Map<String, Object> findById(Integer id);

	/**
	 * 新增用户
	 */
	void save(Map<String, Object> user);

	/**
	 * 更新用户
	 */
	void update(Map<String, Object> user);

	/**
	 * 删除用户（批量）
	 */
	void delete(Integer[] ids);

	/**
	 * 修改密码
	 */
	void updatePassword(Long userId, String newPassword);
}
