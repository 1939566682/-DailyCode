package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.SearchParamsService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ResultVO;
import org.example.vo.SearchParamsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class SearchParamsController {
    @Autowired
    private SearchParamsService searchParamsService;

    @GetMapping("/searchparams/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<SearchParamsVO> result = searchParamsService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/searchparams/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        searchParamsService.delete(ids);
        return R.ok();
    }

    @GetMapping("/searchparams/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        SearchParamsVO searchparams = searchParamsService.findById(id);
        return R.okNamed("searchparams", searchparams);
    }

    @PostMapping("/searchparams/save")
    public ResultVO<Object> save(@RequestBody SearchParamsVO vo) {
        searchParamsService.save(vo);
        return R.ok();
    }

    @PostMapping("/searchparams/update")
    public ResultVO<Object> update(@RequestBody SearchParamsVO vo) {
        searchParamsService.update(vo);
        return R.ok();
    }
}
