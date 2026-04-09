package org.example.service;

import org.example.common.ResponseResult;
import org.example.entity.SysUser;

import java.util.Map;

/**
 * LoginService
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:25
 */

public interface LoginService {
    ResponseResult<Map<String, String>> login(SysUser sysUser);
}
