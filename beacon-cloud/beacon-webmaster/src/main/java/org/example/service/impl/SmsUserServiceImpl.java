package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.SmsUser;
import org.example.entity.SmsUserExample;
import org.example.mapper.SmsUserMapper;
import org.example.service.SmsUserService;
import org.example.util.Md5Utils;
import org.example.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * SmsUserServiceImpl
 *
 * @author Yang QingBo
 * @date 2026-06-15 15:21
 * @description
 */

@Service
public class SmsUserServiceImpl implements SmsUserService {

	@Autowired
	private SmsUserMapper smsUserMapper;

	@Override
	public SmsUser findByUsername(String username) {
		SmsUserExample example = new SmsUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<SmsUser> list = smsUserMapper.selectByExample(example);

		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public PageResult<Map<String, Object>> list(int offset, int limit, String search) {
		SmsUserExample example = new SmsUserExample();
		SmsUserExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeleteEqualTo((byte) 0);
		if (search != null && !search.trim().isEmpty()) {
			criteria.andUsernameLike("%" + search + "%");
		}
		example.setOrderByClause("id desc");
		PageHelper.offsetPage(offset, limit);
		List<SmsUser> list = smsUserMapper.selectByExample(example);
		long total = new PageInfo<>(list).getTotal();
		List<Map<String, Object>> rows = new ArrayList<>();
		for (SmsUser u : list) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("id", u.getId());
			map.put("usercode", u.getUsername());
			map.put("password", u.getPassword());
			map.put("nickname", u.getNickname());
			map.put("realName", u.getNickname());
			map.put("email", u.getExtend2() != null ? u.getExtend2() : "");
			map.put("type", u.getExtend1() != null ? Integer.parseInt(u.getExtend1()) : 2);
			map.put("status", u.getIsDelete() != null && u.getIsDelete() == 0 ? 1 : 0);
			map.put("clientid", u.getCreateId());
			rows.add(map);
		}
		return new PageResult<>(total, rows);
	}

	@Override
	public Map<String, Object> findById(Integer id) {
		SmsUser u = smsUserMapper.selectByPrimaryKey(id);
		if (u == null) return null;
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("id", u.getId());
		map.put("usercode", u.getUsername());
		map.put("password", u.getPassword());
		map.put("nickname", u.getNickname());
		map.put("realName", u.getNickname());
		map.put("email", u.getExtend2() != null ? u.getExtend2() : "");
		map.put("type", u.getExtend1() != null ? Integer.parseInt(u.getExtend1()) : 2);
		map.put("status", u.getIsDelete() != null && u.getIsDelete() == 0 ? 1 : 0);
		map.put("clientid", u.getCreateId());
		return map;
	}

	@Override
	public void save(Map<String, Object> user) {
		SmsUser u = new SmsUser();
		u.setUsername((String) user.get("usercode"));
		String pwd = (String) user.get("password");
		if (pwd != null && !pwd.equals("******")) {
			String salt = UUID.randomUUID().toString().substring(0, 10);
			u.setSalt(salt);
			u.setPassword(Md5Utils.encrypt(pwd, salt));
		}
		u.setNickname((String) user.getOrDefault("realName", ""));
		u.setExtend1(user.get("type") != null ? String.valueOf(user.get("type")) : "2");
		u.setExtend2((String) user.get("email"));
		u.setIsDelete((byte) 0);
		u.setCreated(new Date());
		smsUserMapper.insertSelective(u);
	}

	@Override
	public void update(Map<String, Object> user) {
		Integer id = ((Number) user.get("id")).intValue();
		SmsUser u = smsUserMapper.selectByPrimaryKey(id);
		if (u == null) return;
		if (user.containsKey("usercode")) {
			u.setUsername((String) user.get("usercode"));
		}
		if (user.containsKey("realName")) {
			u.setNickname((String) user.get("realName"));
		}
		if (user.containsKey("email")) {
			u.setExtend2((String) user.get("email"));
		}
		if (user.containsKey("type")) {
			u.setExtend1(String.valueOf(user.get("type")));
		}
		if (user.containsKey("status")) {
			u.setIsDelete(((Number) user.get("status")).intValue() == 0 ? (byte) 1 : (byte) 0);
		}
		String pwd = (String) user.get("password");
		if (pwd != null && !pwd.equals("******") && !pwd.isEmpty()) {
			String salt = UUID.randomUUID().toString().substring(0, 10);
			u.setSalt(salt);
			u.setPassword(Md5Utils.encrypt(pwd, salt));
		}
		u.setUpdated(new Date());
		smsUserMapper.updateByPrimaryKeySelective(u);
	}

	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			smsUserMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void updatePassword(Long userId, String newPassword) {
		Integer id = userId.intValue();
		SmsUser u = smsUserMapper.selectByPrimaryKey(id);
		if (u == null) return;
		String salt = UUID.randomUUID().toString().substring(0, 10);
		u.setSalt(salt);
		u.setPassword(Md5Utils.encrypt(newPassword, salt));
		u.setUpdated(new Date());
		smsUserMapper.updateByPrimaryKeySelective(u);
	}
}
