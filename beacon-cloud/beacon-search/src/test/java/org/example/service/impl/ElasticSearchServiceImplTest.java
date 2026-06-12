package org.example.service.impl;


import org.example.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

/**
 * SearchServiceTest
 *
 * @author Yang QingBo
 * @date 2026-06-08 15:05
 * @description
 */

@SpringBootTest
class ElasticSearchServiceImplTest {
	
	@Autowired
	private SearchService searchService;
	
	@Test
	void index() throws IOException {
		searchService.index("sms_submit_log_2026","3","{\"clientId\": 3}");
	}
	
	@Test
	void exists() throws IOException {
		System.out.println(searchService.exists("sms_submit_log_2026", "322538202306969"));
	}
	
	@Test
	void getYear() {
		String s = LocalDate.now().getYear() + "";
		System.out.println(s);
	}
}
