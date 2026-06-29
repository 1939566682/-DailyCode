package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.SmsTempService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ResultVO;
import org.example.vo.SmsTempVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * SmsTempController
 * 路径前缀: /sys/smstemp
 * 短信模板管理，对应前端 temp/smstemp.js
 * TODO: 需确认数据库 sms_temp 表是否已创建
 */
@Slf4j
@RestController
@RequestMapping("/sys/smstemp")
public class SmsTempController {

    @Autowired
    private SmsTempService smsTempService;

    /**
     * 模板列表（分页）
     * 前端: smstemp.js → GET /sys/smstemp/list → 期望 {total, rows}
     */
    @GetMapping("/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                 @RequestParam(defaultValue = "10") int limit,
                                 @RequestParam(required = false) String search) {
        PageResult<SmsTempVO> result = smsTempService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    /**
     * 删除模板
     * 前端: smstemp.js → POST /sys/smstemp/del → 期望 {code:0}
     */
    @PostMapping("/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        smsTempService.delete(ids);
        return R.ok();
    }

    /**
     * 模板详情
     * 前端: smstemp.js → GET /sys/smstemp/info/{id} → 期望 r.smstemplate
     * 注意: 前端用 r.smstemplate（注意是 smstemplate 不是 smstemp）
     */
    @GetMapping("/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        SmsTempVO smsTemp = smsTempService.findById(id);
        return R.okNamed("smstemplate", smsTemp);
    }

    /**
     * 新增模板
     * 前端: smstemp.js → POST /sys/smstemp/save → 期望 {code:0}
     */
    @PostMapping("/save")
    public ResultVO<Object> save(@RequestBody SmsTempVO smsTempVO) {
        smsTempService.save(smsTempVO);
        return R.ok();
    }

    /**
     * 更新模板
     * 前端: smstemp.js → POST /sys/smstemp/update → 期望 {code:0}
     */
    @PostMapping("/update")
    public ResultVO<Object> update(@RequestBody SmsTempVO smsTempVO) {
        smsTempService.update(smsTempVO);
        return R.ok();
    }
}
