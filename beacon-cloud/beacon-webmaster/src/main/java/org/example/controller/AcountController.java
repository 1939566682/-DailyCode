package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ClientAccountRecordService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.AcountVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class AcountController {
    @Autowired
    private ClientAccountRecordService acountService;

    @GetMapping("/acount/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<AcountVO> result = acountService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/acount/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        acountService.delete(ids);
        return R.ok();
    }

    @GetMapping("/acount/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        AcountVO acount = acountService.findById(id);
        return R.okNamed("acount", acount);
    }

    @PostMapping("/acount/save")
    public ResultVO<Object> save(@RequestBody AcountVO vo) {
        acountService.save(vo);
        return R.ok();
    }

    @PostMapping("/acount/update")
    public ResultVO<Object> update(@RequestBody AcountVO vo) {
        acountService.update(vo);
        return R.ok();
    }
}
