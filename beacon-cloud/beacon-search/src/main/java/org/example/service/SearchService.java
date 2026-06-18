package org.example.service;

import java.io.IOException;
import java.util.Map;

/**
 * SearchService
 *
 * @author Yang QingBo
 * @date 2026-06-08 14:38
 * @description
 */

public interface SearchService {
	
	/**
	 * 向es中添加一行文档
	 * @param index 文档索引
	 * @param id 文档id
	 * @param json 具体文档内容
	 */
	void index(String index,String id,String json) throws IOException;
	
	/**
	 * 查看指定索引中的文档是否存在
	 * @param index
	 * @param id
	 * @return  true：代表存在 false：代表不存在
	 * @throws IOException
	 */
	boolean exists(String index,String id) throws IOException;
	
	/**
	 * 修改文档信息
	 * @param index  文档索引
	 * @param id  文档id
	 * @param doc  要修改的key-value集合
	 * @throws IOException
	 */
	void update(String index, String id, Map<String,Object> doc) throws IOException;
	
	/**
	 * 根据页面条件查询短信记录信息
	 * @param params
	 * @return
	 */
	Map<String, Object> findSmsByParameters(Map<String, Object> params);
}
