package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.SmsMenu;
import org.example.service.MenuService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * MenuController
 *
 * @description 菜单管理Controller - 对应前端 menu.js
 */

@Slf4j
@RestController
@RequestMapping("/sys")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 菜单列表（分页）
     * 前端: menu.js bootstrapTable → 期望 {total, rows}
     */
    @GetMapping("/menu/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<SmsMenu> result = menuService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    /**
     * 批量删除菜单
     * 前端: menu.js → POST /sys/menu/del → 期望 {code:0}
     */
    @PostMapping("/menu/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        menuService.delete(ids);
        return R.ok();
    }

    /**
     * 菜单详情
     * 前端: menu.js → GET /sys/menu/info/{id} → 期望 r.menu
     */
    @GetMapping("/menu/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        SmsMenu menu = menuService.findById(id);
        return R.okNamed("menu", menu);
    }

    /**
     * 保存菜单
     * 前端: menu.js → POST /sys/menu/save → 期望 {code:0}
     */
    @PostMapping("/menu/save")
    public ResultVO<Object> save(@RequestBody SmsMenu menu) {
        menuService.save(menu);
        return R.ok();
    }

    /**
     * 更新菜单
     * 前端: menu.js → POST /sys/menu/update → 期望 {code:0}
     */
    @PostMapping("/menu/update")
    public ResultVO<Object> update(@RequestBody SmsMenu menu) {
        menuService.update(menu);
        return R.ok();
    }

    /**
     * 查询所有菜单（树形结构）
     * 前端: menu.js → GET /sys/menu/select → 期望 r.menuList
     */
    @GetMapping("/menu/select")
    public Map<String, Object> select() {
        List<SmsMenu> menuList = menuService.selectAll();
        return R.okNamed("menuList", menuList);
    }
}
