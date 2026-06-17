package org.example.service.impl;

import org.example.entity.ClientBusiness;
import org.example.entity.ClientBusinessExample;
import org.example.mapper.ClientBusinessMapper;
import org.example.service.ClientBusinessService;
import org.example.vo.ClientBusinessVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * ClientBusinessServiceImol
 *
 * @author Yang QingBo
 * @date 2026-06-17 19:36
 * @description
 */

@Service
public class ClientBusinessServiceImpl implements ClientBusinessService {
	
	@Autowired
	private ClientBusinessMapper clientBusinessMapper;
	
	@Override
	public List<ClientBusiness> findAll() {
		return clientBusinessMapper.selectByExample(null);
	}
	
	@Override
	public List<ClientBusiness> findByUserId(Long userId) {
		ClientBusinessExample example  = new ClientBusinessExample();
		example.createCriteria().andExtend2EqualTo(String.valueOf(userId));
		return clientBusinessMapper.selectByExample(example);
	}
}
