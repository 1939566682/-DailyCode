package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.example.constant.WebMasterConstant;
import org.example.entity.ClientBusiness;
import org.example.entity.SmsUser;
import org.example.enums.ExceptionEnums;
import org.example.service.ClientBusinessService;
import org.example.service.SmsRoleService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ClientBusinessVO;
import org.example.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClientBusinessController
 *
 * @author Yang QingBo
 * @date 2026-06-17 18:17
 * @description 客户信息Controller
 */

@Slf4j
@RestController
@RequestMapping("/sys")
public class ClientBusinessController {
	
	@Autowired
	private SmsRoleService roleService;
	
	@Autowired
	private ClientBusinessService clientBusinessService;
	
	/**
	 * http://localhost:8080/sys/clientbusiness/all?_=1781690552788
	 */
	@GetMapping("/clientbusiness/all")
	public Map<String, Object> all() {
		SmsUser smsUser = (SmsUser) SecurityUtils.getSubject().getPrincipal();
		if (smsUser == null) {
			log.info("【获取客户信息】  用户未登录！！");
			Map<String, Object> map = new java.util.LinkedHashMap<>();
			map.put("code", ExceptionEnums.NOT_LOGIN.getCode());
			map.put("msg", ExceptionEnums.NOT_LOGIN.getMessage());
			return map;
		}
		Long userId = smsUser.getId();
		Set<String> roleNameSet = roleService.getRoleName(userId);
		List<ClientBusiness> list;
		if (roleNameSet != null && roleNameSet.contains(WebMasterConstant.ROOT)) {
			list = clientBusinessService.findAll();
		} else {
			list = clientBusinessService.findByUserId(userId);
		}
		List<ClientBusinessVO> data = new ArrayList<>();
		for (ClientBusiness clientBusiness : list) {
			ClientBusinessVO clientBusinessVO = new ClientBusinessVO();
			BeanUtils.copyProperties(clientBusiness, clientBusinessVO);
			data.add(clientBusinessVO);
		}
		return R.okNamed("sites", data);
	}

	@GetMapping("/clientbusiness/list")
	public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
								  @RequestParam(defaultValue = "10") int limit,
								  @RequestParam(required = false) String search) {
		PageResult<ClientBusinessVO> result = clientBusinessService.list(offset, limit, search);
		return R.ok(result.getTotal(), result.getRows());
	}

	@PostMapping("/clientbusiness/del")
	public ResultVO<Object> del(@RequestBody Long[] ids) {
		clientBusinessService.delete(ids);
		return R.ok();
	}

	@GetMapping("/clientbusiness/info/{id}")
	public Map<String, Object> info(@PathVariable Long id) {
		ClientBusinessVO clientbusiness = clientBusinessService.findById(id);
		return R.okNamed("clientbusiness", clientbusiness);
	}

	@PostMapping("/clientbusiness/save")
	public ResultVO<Object> save(@RequestBody ClientBusinessVO clientBusinessVO) {
		clientBusinessService.save(clientBusinessVO);
		return R.ok();
	}

	@PostMapping("/clientbusiness/update")
	public ResultVO<Object> update(@RequestBody ClientBusinessVO clientBusinessVO) {
		clientBusinessService.update(clientBusinessVO);
		return R.ok();
	}
}
