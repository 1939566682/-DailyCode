package org.example.hjycommunity.web.controller.system;

import org.example.hjycommunity.common.core.controller.BaseController;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.system.domain.pojo.SysDept;
import org.example.hjycommunity.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SysDeptController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 09:47
 */

@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 获取物业公司信息
     * @URL http://localhost/dev-api/system/dept/list
     * @param sysDept
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<SysDept>> findDeptList(SysDept sysDept){
        List<SysDept> sysDepts = sysDeptService.selectDeptList(sysDept);
        return BaseResponse.success(sysDepts);
    }

}
