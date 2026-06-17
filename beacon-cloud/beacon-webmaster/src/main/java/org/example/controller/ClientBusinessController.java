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
import org.example.vo.ClientBusinessVO;
import org.example.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
	public ResultVO<Object> all() {
		// 1、拿到当前用户的信息
		SmsUser smsUser = (SmsUser) SecurityUtils.getSubject().getPrincipal();
		if (smsUser == null) {
			log.info("【获取客户信息】  用户未登录！！");
			return R.error(ExceptionEnums.NOT_LOGIN);
		}
		Long userId = smsUser.getId();
		// 2、查询当前用户的角色信息
		Set<String> roleNameSet = roleService.getRoleName(userId);
		
		// 3、根据角色信息查询数据即可
		List<ClientBusiness> list = null;
		if (roleNameSet != null && roleNameSet.contains(WebMasterConstant.ROOT)) {
			// 查询全部即可
			list = clientBusinessService.findAll();
		} else {
			// 根据用户id查询指定的公司信息
			list = clientBusinessService.findByUserId(userId);
		}
		List<ClientBusinessVO> data = new ArrayList<>();
		for (ClientBusiness clientBusiness : list) {
			ClientBusinessVO clientBusinessVO = new ClientBusinessVO();
			BeanUtils.copyProperties(clientBusiness, clientBusinessVO);
			data.add(clientBusinessVO);
		}
		// 4、响应数据
		return R.ok(data);
	}
}
