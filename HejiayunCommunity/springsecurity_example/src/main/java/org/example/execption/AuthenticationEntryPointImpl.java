package org.example.execption;

import com.alibaba.fastjson.JSON;
import org.example.common.ResponseResult;
import org.example.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationEntryPointImpl
 * 自定义认证过程异常处理
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-10 17:36
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseResult<String> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED,"认证失败，请重新登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
