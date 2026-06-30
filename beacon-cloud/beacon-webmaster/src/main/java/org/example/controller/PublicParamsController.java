package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.PublicParamsService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.PublicParamsVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class PublicParamsController {
    @Autowired
    private PublicParamsService publicParamsService;

    @GetMapping("/publicparams/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<PublicParamsVO> result = publicParamsService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/publicparams/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        publicParamsService.delete(ids);
        return R.ok();
    }

    @GetMapping("/publicparams/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        PublicParamsVO param = publicParamsService.findById(id);
        return R.okNamed("param", param);
    }

    @PostMapping("/publicparams/save")
    public ResultVO<Object> save(@RequestBody PublicParamsVO vo) {
        publicParamsService.save(vo);
        return R.ok();
    }

    @PostMapping("/publicparams/update")
    public ResultVO<Object> update(@RequestBody PublicParamsVO vo) {
        publicParamsService.update(vo);
        return R.ok();
    }
}
