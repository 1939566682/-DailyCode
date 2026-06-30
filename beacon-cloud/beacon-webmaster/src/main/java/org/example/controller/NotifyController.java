package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.NotifyService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.NotifyVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class NotifyController {
    @Autowired
    private NotifyService notifyService;

    @GetMapping("/notify/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<NotifyVO> result = notifyService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/notify/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        notifyService.delete(ids);
        return R.ok();
    }

    @GetMapping("/notify/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        NotifyVO notify = notifyService.findById(id);
        return R.okNamed("notify", notify);
    }

    @PostMapping("/notify/save")
    public ResultVO<Object> save(@RequestBody NotifyVO vo) {
        notifyService.save(vo);
        return R.ok();
    }

    @PostMapping("/notify/update")
    public ResultVO<Object> update(@RequestBody NotifyVO vo) {
        notifyService.update(vo);
        return R.ok();
    }
}
