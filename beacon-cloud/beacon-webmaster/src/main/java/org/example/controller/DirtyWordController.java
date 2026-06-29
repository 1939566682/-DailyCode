package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.DirtyWordService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.DirtyWordVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class DirtyWordController {
    @Autowired
    private DirtyWordService dirtyWordService;

    @GetMapping("/message/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<DirtyWordVO> result = dirtyWordService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/message/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        dirtyWordService.delete(ids);
        return R.ok();
    }

    @GetMapping("/message/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        DirtyWordVO message = dirtyWordService.findById(id);
        return R.okNamed("message", message);
    }

    @PostMapping("/message/save")
    public ResultVO<Object> save(@RequestBody DirtyWordVO dirtyWordVO) {
        dirtyWordService.save(dirtyWordVO);
        return R.ok();
    }

    @PostMapping("/message/update")
    public ResultVO<Object> update(@RequestBody DirtyWordVO dirtyWordVO) {
        dirtyWordService.update(dirtyWordVO);
        return R.ok();
    }
}
