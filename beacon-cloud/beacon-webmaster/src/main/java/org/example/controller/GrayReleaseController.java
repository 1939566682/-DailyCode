package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.GrayReleaseService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.GrayReleaseVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class GrayReleaseController {
    @Autowired
    private GrayReleaseService grayReleaseService;

    @GetMapping("/grayrelease/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<GrayReleaseVO> result = grayReleaseService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/grayrelease/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        grayReleaseService.delete(ids);
        return R.ok();
    }

    @GetMapping("/grayrelease/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        GrayReleaseVO grayrelease = grayReleaseService.findById(id);
        return R.okNamed("grayrelease", grayrelease);
    }

    @PostMapping("/grayrelease/save")
    public ResultVO<Object> save(@RequestBody GrayReleaseVO vo) {
        grayReleaseService.save(vo);
        return R.ok();
    }

    @PostMapping("/grayrelease/update")
    public ResultVO<Object> update(@RequestBody GrayReleaseVO vo) {
        grayReleaseService.update(vo);
        return R.ok();
    }
}
