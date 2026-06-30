package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ActivityService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ActivityVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/activity/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<ActivityVO> result = activityService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/activity/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        activityService.delete(ids);
        return R.ok();
    }

    @GetMapping("/activity/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        ActivityVO activity = activityService.findById(id);
        return R.okNamed("activity", activity);
    }

    @PostMapping("/activity/save")
    public ResultVO<Object> save(@RequestBody ActivityVO vo) {
        activityService.save(vo);
        return R.ok();
    }

    @PostMapping("/activity/update")
    public ResultVO<Object> update(@RequestBody ActivityVO vo) {
        activityService.update(vo);
        return R.ok();
    }
}
