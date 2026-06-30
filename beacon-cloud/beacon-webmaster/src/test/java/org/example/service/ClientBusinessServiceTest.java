package org.example.service;

import org.example.entity.ClientBusiness;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * ClientBusinessServiceTest
 *
 * @author Yang QingBo
 * @date 2026-06-17 20:09
 * @description
 */

@SpringBootTest
class ClientBusinessServiceTest {
	
	@Autowired
	private ClientBusinessService clientBusinessService;
	
	@Test
	void findAll() {
		List<ClientBusiness> all = clientBusinessService.findAll();
		all.forEach(System.out::println);
	}
	
	@Test
	void findByUserId() {
		List<ClientBusiness> clientBusinesses = clientBusinessService.findByUserId(2L);
		clientBusinesses.forEach(System.out::println);
	}
}
