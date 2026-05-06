package org.example.linuxtest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.linuxtest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class LinuxTestApplicationTests {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	void contextLoads() {
	}
	
	@RequestMapping("/test")
	public static void test(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.getWriter().write("OK!");
	}
	
	@Test
	public void  test1() {
		StringBuilder result = new StringBuilder("Data:");
		List<String> strings = userMapper.selectAllUser();
		for (String s : strings) {
			result.append(s);
		}
		System.out.println(result);
	}
	
}
