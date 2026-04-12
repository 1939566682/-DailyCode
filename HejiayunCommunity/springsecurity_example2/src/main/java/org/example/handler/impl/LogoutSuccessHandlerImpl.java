package org.example.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * LogoutSuccessHandlerImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 16:02
 */

 @Component
 public class LogoutSuccessHandlerImpl  implements LogoutSuccessHandler {
 
     @Override
     public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                 Authentication authentication) throws IOException {
         
         Map<String, Object> result = new HashMap<String, Object>();
         result.put("msg", "注销成功");
         result.put("status", 200);
         response.setContentType("application/json;charset=UTF-8");
         String s = new ObjectMapper().writeValueAsString(result);
         response.getWriter().println(s);
     }
 }
