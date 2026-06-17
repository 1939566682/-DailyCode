package org.example.service;

import org.example.entity.ClientBusiness;
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
}
