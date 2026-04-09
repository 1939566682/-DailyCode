package org.example.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * WebUtils
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 15:25
 */

@Slf4j
public class WebUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
