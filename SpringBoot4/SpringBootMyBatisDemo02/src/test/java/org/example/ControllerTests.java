package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/user/1")
                .accept(MediaType.APPLICATION_JSON);// 指定请求的Accept头信息


        // 发送请求，获取请求结果
        ResultActions perform = mockMvc.perform(request);


        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());

        // 表示执行完成后返回相应的结果
        MvcResult mvcResult = perform.andReturn();

        // 得到执行后的响应
        MockHttpServletResponse response = mvcResult.getResponse();
        //response.setCharacterEncoding(WebUtils.DEFAULT_CHARACTER_ENCODING);

        System.out.println("响应状态：{}" + response.getStatus());
        System.out.println("响应内容：{}" + response.getContentAsString());
    }

    @BeforeEach
    public void before(WebApplicationContext context) {
        System.out.println("单元测试开始...");
        //全局拦截 (推荐)
        //创建mockMvc，增加filter----防止响应内容中文乱码  （下图”孙晓飞“位置）
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    public void after() {
        System.out.println("单元测试结束...");
    }

}