package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.StrategyFilterService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ResultVO;
import org.example.vo.StrategyFilterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class StrategyFilterController {
    @Autowired
    private StrategyFilterService strategyFilterService;

    @GetMapping("/stragetyfilter/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<StrategyFilterVO> result = strategyFilterService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/stragetyfilter/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        strategyFilterService.delete(ids);
        return R.ok();
    }

    @GetMapping("/stragetyfilter/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        StrategyFilterVO filter = strategyFilterService.findById(id);
        return R.okNamed("filter", filter);
    }

    @PostMapping("/stragetyfilter/save")
    public ResultVO<Object> save(@RequestBody StrategyFilterVO vo) {
        strategyFilterService.save(vo);
        return R.ok();
    }

    @PostMapping("/stragetyfilter/update")
    public ResultVO<Object> update(@RequestBody StrategyFilterVO vo) {
        strategyFilterService.update(vo);
        return R.ok();
    }
}
