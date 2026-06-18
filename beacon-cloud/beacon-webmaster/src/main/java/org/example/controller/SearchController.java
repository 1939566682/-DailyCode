package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.example.client.SearchClient;
import org.example.constant.WebMasterConstant;
import org.example.entity.ClientBusiness;
import org.example.entity.SmsUser;
import org.example.enums.ExceptionEnums;
import org.example.service.ClientBusinessService;
import org.example.service.SmsRoleService;
import org.example.util.R;
import org.example.vo.ResultVO;
import org.example.vo.SearchSmsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * SearchController
 *
 * @author Yang QingBo
 * @date 2026-06-17 22:09
 * @description
 */

@Slf4j
@RestController
@RequestMapping("/sys")
public class SearchController {
	
	@Autowired
	private SmsRoleService roleService;
	
	@Autowired
	private ClientBusinessService clientBusinessService;
	
	@Autowired
	private SearchClient searchClient;
	
	/**
	 * http://localhost:8080/sys/search/list?size=10&from=0&content=&starttime=&stoptime=&mobile=&clientID=2
	 */
	@GetMapping("/search/list")
	public ResultVO<Object> list(@RequestParam Map<String, Object> map) {
		// 1、判断当前登录的角色能否查询对应的客户信息
		// 1.1 查看用户是否登录
		SmsUser smsUser = (SmsUser) SecurityUtils.getSubject().getPrincipal();
		if (smsUser == null) {
			log.info("【搜索短信信息】  用户未登录！！");
			return R.error(ExceptionEnums.NOT_LOGIN);
		}
		
		// 查看当前请求参数中携带的clientID
		String clientIDStr = map.get("clientID").toString();
		Long clientID = null;
		if (!StringUtils.isEmpty(clientIDStr)) {
			clientID = Long.parseLong(clientIDStr);
		}
		
		// 1.2 拿到用户的id标识  查看用户的角色是否是管理员
		Set<String> roleNames = roleService.getRoleName(smsUser.getId());
		if (roleNames != null && !roleNames.contains(WebMasterConstant.ROOT)) {
			// 1.3 如果不是管理员  需要查询当前用户对应的公司信息  匹配参数中的公司id是否一致
			List<ClientBusiness> clients = clientBusinessService.findByUserId(smsUser.getId());
			
			if (clientID == null || clientID <= 0) {
				// 没传递clientID  默认查询当前用户所拥有的所有公司信息
				List<Long> list = new ArrayList<>(); // 临时存储当前用户所拥有的所有公司信息
				clients.forEach(client -> list.add(client.getId()));
				map.put("clientID", list);
			} else {
				boolean flag = false;
				// 传递了clientID  判断当前用户所拥有的公司id是否包含该clientID
				for (ClientBusiness client : clients) {
					if (Objects.equals(client.getId(), clientID)) {
						// 满足当前用户的操作
						flag = true;
						break;
					}
				}
				if (!flag) {
					log.info("【搜索短信信息】  用户权限不足！！");
					return R.error(ExceptionEnums.SMS_NO_PERMISSION);
				}
			}
			
		}
		
		// 2、调用搜索模块查询数据  返回total和rows
		Map<String, Object> data = searchClient.findSmsByParams(map);
		
		// 3、判断返回的total 如果total为0 正常返回
		long total = Long.parseLong(String.valueOf(data.get("total")));
		if (total == 0) {
			return R.ok(0L,null);
		}
		
		// 4、如果数据正常  做返回数据的封装  声明了一个SearchSmsVO的实体类
		List<Map> list = (List<Map>) data.get("rows");
		List<SearchSmsVO> rows = new ArrayList<>();
		for (Map row : list) {
			SearchSmsVO searchSmsVO = new SearchSmsVO();
			try {
				BeanUtils.copyProperties(searchSmsVO, row);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			rows.add(searchSmsVO);
		}
		
		// 5、响应数据
		return R.ok(total,rows);
	}
	
}
