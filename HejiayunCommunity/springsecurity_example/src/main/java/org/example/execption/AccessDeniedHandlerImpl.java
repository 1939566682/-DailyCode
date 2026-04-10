package org.example.execption;

import com.alibaba.fastjson.JSON;
import org.example.common.ResponseResult;
import org.example.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccessDeniedHandlerImpl
 * 自定义授权过程中的异常
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-10 17:45
 */

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult<String> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "权限不足，禁止访问");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
