package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * SmsSearchController
 *
 * @author Yang QingBo
 * @date 2026-06-18 18:20
 * @description
 */

@Slf4j
@RestController
public class SmsSearchController {
	
	@Autowired
	private SearchService searchService;
	
	/**
	 * 根据页面条件查询短信记录信息
	 * @param params
	 * @return Map<String,Object> total：数据条数  rows：List集合 放着每一行需要展示的数据
	 */
	@PostMapping("/search/sms/list")
	public Map<String,Object> findSmsByParams(@RequestBody Map<String,Object> params){
		// 调用搜索模块完成查询
		return searchService.findSmsByParameters(params);
	}
	
}
