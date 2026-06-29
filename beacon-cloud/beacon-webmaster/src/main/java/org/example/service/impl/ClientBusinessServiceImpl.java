package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.ClientBalance;
import org.example.entity.ClientBalanceExample;
import org.example.entity.ClientBusiness;
import org.example.entity.ClientBusinessExample;
import org.example.mapper.ClientBalanceMapper;
import org.example.mapper.ClientBusinessMapper;
import org.example.service.ClientBusinessService;
import org.example.util.PageResult;
import org.example.vo.ClientBusinessVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClientBusinessServiceImpl
 *
 * @author Yang QingBo
 * @date 2026-06-17 19:36
 * @description
 */

@Service
public class ClientBusinessServiceImpl implements ClientBusinessService {
	
	@Autowired
	private ClientBusinessMapper clientBusinessMapper;
	@Autowired
	private ClientBalanceMapper clientBalanceMapper;
	
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

	@Override
	public PageResult<ClientBusinessVO> list(int offset, int limit, String search) {
		ClientBusinessExample example = new ClientBusinessExample();
		if (search != null && !search.isEmpty()) {
			example.createCriteria().andCorpnameLike("%" + search + "%");
		}
		example.setOrderByClause("id desc");
		PageHelper.offsetPage(offset, limit);
		List<ClientBusiness> list = clientBusinessMapper.selectByExample(example);
		long total = new PageInfo<>(list).getTotal();
		List<ClientBusinessVO> voList = new ArrayList<>();
		for (ClientBusiness cb : list) {
			ClientBusinessVO vo = toVO(cb);
			voList.add(vo);
		}
		return new PageResult<>(total, voList);
	}

	private ClientBusinessVO toVO(ClientBusiness cb) {
		ClientBusinessVO vo = new ClientBusinessVO();
		vo.setId(cb.getId());
		vo.setCorpname(cb.getCorpname());
		vo.setUsercode(cb.getApikey());
		vo.setPwd("******");
		vo.setIpaddress(cb.getIpAddress());
		vo.setIsreturnstatus(cb.getIsCallback() != null ? cb.getIsCallback().intValue() : null);
		vo.setReceivestatusurl(cb.getCallbackUrl());
		vo.setPriority(cb.getExtend3() != null ? Integer.parseInt(cb.getExtend3()) : null);
		vo.setUsertype(cb.getExtend4() != null ? Integer.parseInt(cb.getExtend4()) : null);
		vo.setState(cb.getIsDelete() != null ? (cb.getIsDelete() == 0 ? 1 : 0) : null);
		vo.setMobile(cb.getClientPhone());
		// 联查余额
		if (cb.getId() != null) {
			ClientBalanceExample balanceExample = new ClientBalanceExample();
			balanceExample.createCriteria().andclientIdEqualTo(cb.getId());
			List<ClientBalance> balances = clientBalanceMapper.selectByExample(balanceExample);
			if (balances != null && !balances.isEmpty()) {
				vo.setMoney(balances.get(0).getBalance());
			}
		}
		return vo;
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			clientBusinessMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public ClientBusinessVO findById(Long id) {
		ClientBusiness cb = clientBusinessMapper.selectByPrimaryKey(id);
		if (cb == null) return null;
		return toVO(cb);
	}

	@Override
	public void save(ClientBusinessVO clientBusinessVO) {
		ClientBusiness cb = new ClientBusiness();
		cb.setCorpname(clientBusinessVO.getCorpname());
		cb.setApikey(clientBusinessVO.getUsercode());
		cb.setIpAddress(clientBusinessVO.getIpaddress());
		cb.setIsCallback(clientBusinessVO.getIsreturnstatus() != null ? clientBusinessVO.getIsreturnstatus().byteValue() : null);
		cb.setCallbackUrl(clientBusinessVO.getReceivestatusurl());
		cb.setClientPhone(clientBusinessVO.getMobile());
		cb.setExtend3(clientBusinessVO.getPriority() != null ? String.valueOf(clientBusinessVO.getPriority()) : null);
		cb.setExtend4(clientBusinessVO.getUsertype() != null ? String.valueOf(clientBusinessVO.getUsertype()) : null);
		cb.setIsDelete(clientBusinessVO.getState() != null ? (byte)(clientBusinessVO.getState() == 1 ? 0 : 1) : (byte)0);
		cb.setCreated(new Date());
		clientBusinessMapper.insertSelective(cb);
	}

	@Override
	public void update(ClientBusinessVO clientBusinessVO) {
		ClientBusiness cb = clientBusinessMapper.selectByPrimaryKey(clientBusinessVO.getId());
		if (cb == null) return;
		cb.setCorpname(clientBusinessVO.getCorpname());
		if (clientBusinessVO.getUsercode() != null && !"******".equals(clientBusinessVO.getUsercode())) {
			cb.setApikey(clientBusinessVO.getUsercode());
		}
		cb.setIpAddress(clientBusinessVO.getIpaddress());
		cb.setIsCallback(clientBusinessVO.getIsreturnstatus() != null ? clientBusinessVO.getIsreturnstatus().byteValue() : null);
		cb.setCallbackUrl(clientBusinessVO.getReceivestatusurl());
		cb.setClientPhone(clientBusinessVO.getMobile());
		cb.setExtend3(clientBusinessVO.getPriority() != null ? String.valueOf(clientBusinessVO.getPriority()) : null);
		cb.setExtend4(clientBusinessVO.getUsertype() != null ? String.valueOf(clientBusinessVO.getUsertype()) : null);
		cb.setIsDelete(clientBusinessVO.getState() != null ? (byte)(clientBusinessVO.getState() == 1 ? 0 : 1) : (byte)0);
		cb.setUpdated(new Date());
		clientBusinessMapper.updateByPrimaryKeySelective(cb);
	}
}
