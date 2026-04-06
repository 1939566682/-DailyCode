package org.example.hjycommunity.web.controller.system;

import org.example.hjycommunity.common.core.controller.BaseController;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.system.domain.dto.SysAreaDTO;
import org.example.hjycommunity.system.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SysAreaController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 22:24
 */

@RestController
@RequestMapping("/system/area")
public class SysAreaController extends BaseController {

    @Autowired
    private SysAreaService sysAreaService;

    @GetMapping("/tree")
    public BaseResponse<List<SysAreaDTO>> getAreaAsTree(SysAreaDTO sysAreaDTO) {
        return BaseResponse.success(sysAreaService.findAreaAsTree());
    }

}
