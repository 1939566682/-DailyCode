package org.example.service;

import org.example.entity.ClientBusiness;
import org.example.util.PageResult;
import org.example.vo.ClientBusinessVO;

import java.util.List;

/**
 * ClientBusinessService
 *
 * @author Yang QingBo
 * @date 2026-06-17 19:36
 * @description
 */

public interface ClientBusinessService {
	
	/**
	 * 查询全部客户信息
	 * @return
	 */
	List<ClientBusiness> findAll();
	
	/**
	 * 根据用户id查询客户信息
	 * @param userId
	 * @return
	 */
	List<ClientBusiness> findByUserId(Long userId);

	/**
	 * 分页查询客户接入配置
	 */
	PageResult<ClientBusinessVO> list(int offset, int limit, String search);

	/**
	 * 批量删除客户接入配置
	 */
	void delete(Long[] ids);

	/**
	 * 根据id查询客户接入配置
	 */
	ClientBusinessVO findById(Long id);

	/**
	 * 保存客户接入配置
	 */
	void save(ClientBusinessVO clientBusinessVO);

	/**
	 * 更新客户接入配置
	 */
	void update(ClientBusinessVO clientBusinessVO);
}
