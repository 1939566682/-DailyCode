package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.example.enums.ExceptionEnums;
import org.example.execption.SearchException;
import org.example.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * SearchServiceImpl
 *
 * @author Yang QingBo
 * @date 2026-06-08 14:39
 * @description
 */

@Slf4j
@Service
public class ElasticSearchServiceImpl implements SearchService {
	
	/**
	 * 添加成功的result
	 */
	private final String CREATED = "created";
	private final String UPDATED = "updated";
	
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	
	@Override
	public void index(String index, String id, String json) throws IOException {
		// 1、构建插入数据的Request
		IndexRequest indexRequest = new IndexRequest();
		
		// 2、给Request对象封装索引信息  文档id  以及文档内容
		indexRequest.index(index);
		indexRequest.id(id);
		indexRequest.source(json, XContentType.JSON);
		
		// 3、将封装好的Request的信息发送给es服务
		IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		
		// 4、校验添加是否成功
		String result = response.getResult().getLowercase();
		if (CREATED.equals(result) || UPDATED.equals(result)) {
			log.info("【搜索模块 - 写入数据成功】  index = {}  id = {}  json = {}  result = {}", index, id, json, result);
		}else {
			// 添加失败
			log.error("【搜索模块 - 写入数据失败】  index = {}  id = {}  json = {}  result = {}", index, id, json, result);
			throw new SearchException(ExceptionEnums.SEARCH_INDEX_ERROR);
		}
		
	}
	
	@Override
	public boolean exists(String index, String id) throws IOException {
		// 1、构建查询指定id的文档是否存在的Request
		GetRequest getRequest = new GetRequest();
		
		// 2、给Request对象封装索引信息  文档id
		getRequest.index(index);
		getRequest.id(id);
		
		// 3、将封装好的Request的信息发送给es服务
		// 基于restHighLevelClient将查询指定id的文档是否存在的请求投递过去
		return restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
	}
}
