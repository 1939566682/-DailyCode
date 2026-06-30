package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ApiGatewayFilterService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ApiGatewayFilterVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class ApiGatewayFilterController {
    @Autowired
    private ApiGatewayFilterService apiGatewayFilterService;

    @GetMapping("/apigatewayfilter/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<ApiGatewayFilterVO> result = apiGatewayFilterService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/apigatewayfilter/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        apiGatewayFilterService.delete(ids);
        return R.ok();
    }

    @GetMapping("/apigatewayfilter/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        ApiGatewayFilterVO filter = apiGatewayFilterService.findById(id);
        return R.okNamed("filter", filter);
    }

    @PostMapping("/apigatewayfilter/save")
    public ResultVO<Object> save(@RequestBody ApiGatewayFilterVO vo) {
        apiGatewayFilterService.save(vo);
        return R.ok();
    }

    @PostMapping("/apigatewayfilter/update")
    public ResultVO<Object> update(@RequestBody ApiGatewayFilterVO vo) {
        apiGatewayFilterService.update(vo);
        return R.ok();
    }
}
