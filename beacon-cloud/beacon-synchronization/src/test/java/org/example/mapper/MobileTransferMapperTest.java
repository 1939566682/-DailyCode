package org.example.mapper;

import org.example.client.CacheClient;
import org.example.entity.MobileTransfer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MobileTransferMapperTest
 *
 * @author Yang QingBo
 * @date 2026-06-03 21:21
 * @description
 */

@SpringBootTest
class MobileTransferMapperTest {
	
	@Autowired
	private MobileTransferMapper mobileTransferMapper;
	
	@Autowired
	private CacheClient cacheClient;
	
	@Test
	void find() {
		List<MobileTransfer> list = mobileTransferMapper.findAll();
		for (MobileTransfer mobileTransfer : list) {
			cacheClient.set("transfer:"+mobileTransfer.getTransferNumber(), mobileTransfer.getNowIsp());
		}
	}
}
