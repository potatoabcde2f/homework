package com.my.blog.website.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**首页控制器测试类
 * 用于测试IndexController的接口功能，验证HTTP请求的响应状态和结果
 * 使用Spring Boot Test框架进行集成测试
 * 通过MockMvc模拟HTTP请求，测试控制器层的接口行为
 **/
@RunWith(SpringRunner.class)                    // 使用Spring的测试运行器
@SpringBootTest                                 // 标记为Spring Boot测试类，会加载完整的应用程序上下文
@AutoConfigureMockMvc                          // 自动配置MockMvc，用于模拟HTTP请求
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;                    // 注入MockMvc实例，用于模拟HTTP请求和验证响应

    /**
     * 测试首页接口
     * 验证访问根路径("/")时是否返回HTTP 200状态码
     **/
    @Test
    @Ignore                                     // 忽略此测试用例，可能因为测试环境问题或暂时不需要执行
    public void index() throws Exception {
        // 构建GET请求到根路径，并期望返回200状态码
        mockMvc.perform(MockMvcRequestBuilders.get(""))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}