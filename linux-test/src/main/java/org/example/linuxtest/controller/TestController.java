package org.example.linuxtest.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.linuxtest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * TestController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-06 19:34
 */

@RestController
public class TestController {
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/test")
	public StringBuilder test() throws IOException {
		StringBuilder result = new StringBuilder("Data:");
		List<String> strings = userMapper.selectAllUser();
		for (String s : strings) {
			result.append(s);
		}
		return result;
	}
}
