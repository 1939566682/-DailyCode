package org.example.exceptionhandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 08:41
 */


public class ExceptionHandler03 implements HandlerExceptionResolver {
    @Override
    public @Nullable ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        ModelAndView m = new ModelAndView();
        if (ex instanceof java.lang.ArithmeticException) {
            m.setViewName("myError");
        }
        return m;
    }
}
