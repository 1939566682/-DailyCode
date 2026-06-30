package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.BlackService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.BlackVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class BlackController {
    @Autowired
    private BlackService blackService;

    @GetMapping("/black/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<BlackVO> result = blackService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/black/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        blackService.delete(ids);
        return R.ok();
    }

    @GetMapping("/black/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        BlackVO black = blackService.findById(id);
        return R.okNamed("black", black);
    }

    @PostMapping("/black/save")
    public ResultVO<Object> save(@RequestBody BlackVO blackVO) {
        blackService.save(blackVO);
        return R.ok();
    }

    @PostMapping("/black/update")
    public ResultVO<Object> update(@RequestBody BlackVO blackVO) {
        blackService.update(blackVO);
        return R.ok();
    }
}
