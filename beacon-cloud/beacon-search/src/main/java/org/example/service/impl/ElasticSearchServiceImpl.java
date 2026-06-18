package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.example.constant.RabbitMQConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.SearchException;
import org.example.model.StandardReport;
import org.example.service.SearchService;
import org.example.utils.SearchUtils;
import org.example.utils.ThreadLocalUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.*;

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
	
	/**
	 * 修改成功的result
	 */
	private final String UPDATED = "updated";
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
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
			log.info("【搜索模块 - 写入数据成功】  index = {} id = {} json = {} result = {}", index, id, json, result);
		} else {
			// 添加失败
			log.error("【搜索模块 - 写入数据失败】  index = {} id = {} json = {} result = {}", index, id, json, result);
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
	
	@Override
	public void update(String index, String id, Map<String, Object> doc) throws IOException {
		// 1、基于exist方法 查询当前需要修改的文档是否存在
		boolean exists = exists(index, id);
		if (!exists) {
			// 当前文档不存在
			StandardReport report = ThreadLocalUtils.get();
			if (report.getReUpdate()) {
				// 说明已经是第二次修改且文档依旧不存在
				log.error("【搜索模块 - 修改日志】  第二次修改日志失败 report = {}", report);
			} else {
				// 第一次进行修改时查询到文档不存在 放回到MQ的死信队列  尝试第二次修改
				log.error("【搜索模块 - 修改日志】 修改日志失败 将进行重试 report = {}", report);
				report.setReUpdate(true);
				rabbitTemplate.convertAndSend(RabbitMQConstant.SMS_GATEWAY_NORMAL_EXCHANGE, report);
			}
			ThreadLocalUtils.remove();
			return;
		}
		ThreadLocalUtils.remove();
		// 2、文档存在  可以直接进行修改
		UpdateRequest updateRequest = new UpdateRequest(index, id);
		updateRequest.doc(doc);
		UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
		// 3、校验修改是否成功
		String result = update.getResult().getLowercase();
		if (UPDATED.equals(result)) {
			log.info("【搜索模块 - 修改数据成功】  index = {} id = {} doc = {} result = {}", index, id, doc, result);
		} else {
			// 添加失败
			log.error("【搜索模块 - 修改数据失败】  index = {} id = {} doc = {} result = {}", index, id, doc, result);
			throw new SearchException(ExceptionEnums.SEARCH_UPDATE_ERROR);
		}
	}
	
	/**
	 * 根据页面条件查询短信记录信息
	 *
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> findSmsByParameters(Map<String, Object> params) {
		// 1、声明SearchRequest
		// TODO 后期需要根据传递的时间指定查询哪些索引  如果没传可以指定默认查询前三个月的
		SearchRequest searchRequest = new SearchRequest(SearchUtils.getCurrentYearIndex());
		
		// 2、封装查询条件
		// 2.1 取出全部参数
		Object fromObj = params.get("from");
		Object sizeObj = params.get("size");
		Object contentObj = params.get("content");
		Object mobileObj = params.get("mobile");
		Object startTimeObj = params.get("starttime");
		Object stopTimeObj = params.get("stoptime");
		Object clientIDObj = params.get("clientID");
		
		// 2.2 clientID需要单独操作
		List<Integer> clientIDList = null;
		if (clientIDObj instanceof List) {
			// 传递的是集合
			clientIDList = (List<Integer>) clientIDObj;
		} else if (!ObjectUtils.isEmpty(clientIDObj)) {
			clientIDList = Collections.singletonList(Integer.parseInt(clientIDObj.toString()));
		}
		
		// 2.3 条件封装
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		// ------------------------- 封装查询条件到boolQueryBuilder -------------------------
		//2.3.1 关键字
		if (!ObjectUtils.isEmpty(contentObj)) {
			boolQueryBuilder.must(QueryBuilders.matchQuery("text", contentObj));
			// 高亮设置（短信内容中被检索的文字）
			HighlightBuilder highlightBuilder = new HighlightBuilder();
			highlightBuilder.field("text");
			highlightBuilder.preTags("<span style='color:red'>");
			highlightBuilder.postTags("</span>");
			highlightBuilder.fragmentSize(100);
			sourceBuilder.highlighter(highlightBuilder);
		}
		
		//2.3.2 手机号
		if (!ObjectUtils.isEmpty(mobileObj)) {
			boolQueryBuilder.must(QueryBuilders.prefixQuery("mobile", mobileObj.toString()));
		}
		
		//2.3.6 分页查询
		sourceBuilder.from(Integer.parseInt(fromObj.toString()));
		sourceBuilder.size(Integer.parseInt(sizeObj.toString()));
		
		//2.3.3 开始时间
		if (!ObjectUtils.isEmpty(startTimeObj)) {
			boolQueryBuilder.must(QueryBuilders.rangeQuery("sendTime").gte(startTimeObj));
		}
		
		//2.3.4 结束时间
		if (!ObjectUtils.isEmpty(stopTimeObj)) {
			boolQueryBuilder.must(QueryBuilders.rangeQuery("sendTime").lte(stopTimeObj));
		}
		
		//2.3.5 客户id
		if (!ObjectUtils.isEmpty(clientIDList)) {
			boolQueryBuilder.filter(QueryBuilders.termsQuery("clientId", clientIDList));
		}
		
		// 封装查询条件到boolQueryBuilder
		sourceBuilder.query(boolQueryBuilder);
		searchRequest.source(sourceBuilder);
		
		// 3、执行查询
		
		SearchResponse resp = null;
		try {
			resp = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.error("【搜索模块 - 短信搜索】  查询操作执行失败！ e = {}", e.getMessage());
		}
		
		
		// 4、封装数据
		long total = resp.getHits().getTotalHits().value;
		ArrayList<Map<String, Object>> rows = new ArrayList<>();
		for (SearchHit hit : resp.getHits().getHits()) {
			Map<String, Object> row = hit.getSourceAsMap();
//			List<Integer> sendTime =  Collections.singletonList(Integer.parseInt(row.get("sendTime").));
			List<Integer> sendTime = (List<Integer>) row.get("sendTime");
			String sendTimeStr = "";
			if (sendTime != null) {
				sendTimeStr = listToDateString(sendTime);
			}
			row.put("sendTimeStr", sendTimeStr);
			row.put("corpname", row.get("sign"));
			rows.add(row);
		}
		
		// 5、返回
		HashMap<String, Object> result = new HashMap<>();
		result.put("total", total);
		result.put("rows", rows);
		return result;
	}
	
	private String listToDateString(List<Integer> sendTime) {
		String year = sendTime.get(0).toString();
		Integer monthInt = sendTime.get(1);
		Integer dayInt = sendTime.get(2);
		Integer hourInt = sendTime.get(3);
		Integer minuteInt = sendTime.get(4);
		Integer secondInt = sendTime.get(5);
		
		String month = monthInt / 10 == 0 ? "0" + monthInt : monthInt + "";
		String day = dayInt / 10 == 0 ? "0" + dayInt : dayInt + "";
		String hour = hourInt / 10 == 0 ? "0" + hourInt : hourInt + "";
		String minute = minuteInt / 10 == 0 ? "0" + minuteInt : minuteInt + "";
		String second = secondInt / 10 == 0 ? "0" + secondInt : secondInt + "";
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
	}
	
}
