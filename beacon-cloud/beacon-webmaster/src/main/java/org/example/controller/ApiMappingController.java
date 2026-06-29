package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ApiMappingService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ApiMappingVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class ApiMappingController {
    @Autowired
    private ApiMappingService apiMappingService;

    @GetMapping("/apimapping/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<ApiMappingVO> result = apiMappingService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/apimapping/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        apiMappingService.delete(ids);
        return R.ok();
    }

    @GetMapping("/apimapping/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        ApiMappingVO apimapping = apiMappingService.findById(id);
        return R.okNamed("apimapping", apimapping);
    }

    @PostMapping("/apimapping/save")
    public ResultVO<Object> save(@RequestBody ApiMappingVO vo) {
        apiMappingService.save(vo);
        return R.ok();
    }

    @PostMapping("/apimapping/update")
    public ResultVO<Object> update(@RequestBody ApiMappingVO vo) {
        apiMappingService.update(vo);
        return R.ok();
    }
}
