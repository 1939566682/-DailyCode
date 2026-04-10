package org.example.controller;

import org.example.common.ResponseResult;
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
    @PreAuthorize("hasAnyAuthority('system:role:list','system:user:list')")  // 检查用户是否具有任何一个指定权限
    public String ok() {
        return "OK Spring Security!";
    }

    @RequestMapping("/yes")
    @PreAuthorize("@my_ex.hasAuthority('system:user:list')")  // 检查用户是否具有任何一个指定权限
    public String yes() {
        return "YES Spring Security!";
    }

    @RequestMapping("/role1")
    @PreAuthorize("hasRole('admin')")
    public String role1() {
        return "role1 Spring Security!";
    }

    @RequestMapping("/role2")
    @PreAuthorize("hasAnyRole('admin','zhangsan')")
    public String role2() {
        return "role2 Spring Security!";
    }

    @RequestMapping("/testCors")
    public ResponseResult<String> testCors() {
        return ResponseResult.success("OK Cors");
    }

    @RequestMapping("/level1")
    //当前用户是common角色,并且具有system:role:list或者system:user:list
    @PreAuthorize("hasRole('common') OR hasAnyAuthority('system:role:list','system:user:list')")
    public String level1() {
        return "level1 page";
    }

    @RequestMapping("/level2")
    //当前用户拥有admin或者common角色,或者具有system:role:list权限
    @PreAuthorize("hasAnyRole('admin','common') OR hasAuthority('system:role:list')")
    public String level2() {
        return "level2 page";
    }

}
