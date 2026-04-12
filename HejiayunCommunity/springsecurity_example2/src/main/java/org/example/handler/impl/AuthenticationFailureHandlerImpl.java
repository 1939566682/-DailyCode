package org.example.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthenticationFailureHandlerImpl
 * 自定义 AuthenticationFailureHandlerImpl 实现
 * 自定义认证失败处理器
 * @author Yang QingBo
 * {@code @date} 2026-04-12 15:41
 */

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException {

    Map<String, Object> result = new HashMap<String, Object>();
    result.put("msg", "登录失败: "+exception.getMessage());
    result.put("status", 500);
    response.setContentType("application/json;charset=UTF-8");
    String s = new ObjectMapper().writeValueAsString(result);
    response.getWriter().println(s);
   }
}