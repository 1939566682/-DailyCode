package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.SmsRoleService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.RoleVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * RoleController
 * 路径前缀: /system/role
 * 角色管理，对应前端 sys/role.js
 */
@Slf4j
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private SmsRoleService smsRoleService;

    /**
     * 角色列表（分页+搜索 name, status）
     * 前端: role.js → GET /system/role/list → 期望 {total, rows}
     */
    @GetMapping("/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) Integer status) {
        PageResult<RoleVO> result = smsRoleService.list(offset, limit, name, status);
        return R.ok(result.getTotal(), result.getRows());
    }

    /**
     * 删除角色
     * 前端: role.js → POST /system/role/delete → 期望 {code:0}
     * 注意: 前端JS用 r.status/r.message，但统一返回 code/msg 格式
     */
    @PostMapping("/delete")
    public ResultVO<Object> delete(@RequestBody Long[] ids) {
        smsRoleService.delete(ids);
        return R.ok();
    }

    /**
     * 单个角色详情
     * 前端: role.js → GET /system/role/info/{id} → 期望 r.role
     */
    @GetMapping("/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        RoleVO role = smsRoleService.findById(id);
        return R.okNamed("role", role);
    }

    /**
     * 新增角色
     * 前端: role.js → POST /system/role/add → 期望 {code:0}
     */
    @PostMapping("/add")
    public ResultVO<Object> add(@RequestBody RoleVO roleVO) {
        smsRoleService.save(roleVO);
        return R.ok();
    }

    /**
     * 修改角色
     * 前端: role.js → POST /system/role/update → 期望 {code:0}
     */
    @PostMapping("/update")
    public ResultVO<Object> update(@RequestBody RoleVO roleVO) {
        smsRoleService.update(roleVO);
        return R.ok();
    }

    /**
     * 分配菜单
     * 前端: role.js → GET /system/role/assign_menu?roleId=xx&menuIds=xx&menuIds=yy
     * Spring MVC 会自动将同名参数绑定为数组
     */
    @GetMapping("/assign_menu")
    public ResultVO<Object> assignMenu(@RequestParam Long roleId,
                                        @RequestParam(required = false) Long[] menuIds) {
        if (menuIds == null) {
            menuIds = new Long[0];
        }
        smsRoleService.assignMenu(roleId, menuIds);
        return R.ok();
    }

    /**
     * 查询角色的已分配菜单ID数组
     * 前端: role.js → GET /system/role/role_menu/{id}
     */
    @GetMapping("/role_menu/{id}")
    public Long[] roleMenu(@PathVariable Long id) {
        return smsRoleService.getRoleMenuIds(id);
    }

    /**
     * 查询所有菜单树
     * 前端: role.js → GET /system/role/menu_tree → 期望 r.menuList
     */
    @GetMapping("/menu_tree")
    public Map<String, Object> menuTree() {
        return R.okNamed("menuList", smsRoleService.getMenuTree());
    }
}
