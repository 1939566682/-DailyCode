package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.CodeLimitService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.CodeLimitVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * LimitController
 * 路径前缀: /sys/limit
 * 对应前端 limit.js
 */
@Slf4j
@RestController
@RequestMapping("/sys/limit")
public class LimitController {

    @Autowired
    private CodeLimitService codeLimitService;

    /**
     * 限制列表
     * 前端: limit.js → GET /sys/limit/list → 期望 {total, rows}
     */
    @GetMapping("/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                 @RequestParam(defaultValue = "10") int limit,
                                 @RequestParam(required = false) String search) {
        PageResult<CodeLimitVO> result = codeLimitService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    /**
     * 删除限制
     * 前端: limit.js → POST /sys/limit/del → 期望 {code:0}
     */
    @PostMapping("/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        codeLimitService.delete(ids);
        return R.ok();
    }

    /**
     * 限制详情
     * 前端: limit.js → GET /sys/limit/info/{id} → 期望 r.limit
     */
    @GetMapping("/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        CodeLimitVO limit = codeLimitService.findById(id);
        return R.okNamed("limit", limit);
    }

    /**
     * 新增限制
     * 前端: limit.js → POST /sys/limit/save → 期望 {code:0}
     */
    @PostMapping("/save")
    public ResultVO<Object> save(@RequestBody CodeLimitVO vo) {
        codeLimitService.save(vo);
        return R.ok();
    }

    /**
     * 更新限制
     * 前端: limit.js → POST /sys/limit/update → 期望 {code:0}
     */
    @PostMapping("/update")
    public ResultVO<Object> update(@RequestBody CodeLimitVO vo) {
        codeLimitService.update(vo);
        return R.ok();
    }
}
