package org.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloSecurityController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-08 16:37
 */

@RestController
public class HelloSecurityController {

    /**
     * @return
     * @PreAuthorize() 在方法执行前进行权限的校验
     * hasAuthority() 检查用户是否具有指定权限
     * hasAuthority('test') 检查用户是否具有 test 权限
     * 在方法执行后进行权限的校验可以使用 @PostAuthorize()
     */
    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:user:list')")
    public String hello() {
        return "Hello Spring Security!";
    }

    @RequestMapping("/ok")
    @PreAuthorize("hasAuthority('system:role:list')")
    public String ok() {
        return "OK Spring Security!";
    }
}
