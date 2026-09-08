package dao;

import com.example.demo.dao.DeptDao;
import com.example.demo.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * DeptDaoTest
 *
 * @author Yang QingBo
 * @date 2026-09-08 17:05
 * @description
 */

@SpringBootTest(classes = com.example.demo.DemoApplication.class)
class DeptDaoTest {
	
	@Autowired
	private DeptDao deptDao;
	
	
	@Test
	void queryById() {
		Dept dept = deptDao.queryById(10);
		System.out.println(dept.toString());
	}
	
	@Test
	void count() {
	}
	
	@Test
	void insert() {
	}
	
	@Test
	void insertBatch() {
	}
	
	@Test
	void insertOrUpdateBatch() {
	}
	
	@Test
	void update() {
	}
	
	@Test
	void deleteById() {
	}
}
