package org.example.service;

import java.io.IOException;

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
}
