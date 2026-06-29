package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.PhaseService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.PhaseVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class PhaseController {
    @Autowired
    private PhaseService phaseService;

    @GetMapping("/phase/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<PhaseVO> result = phaseService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/phase/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        phaseService.delete(ids);
        return R.ok();
    }

    @GetMapping("/phase/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        PhaseVO phase = phaseService.findById(id);
        return R.okNamed("phase", phase);
    }

    @PostMapping("/phase/save")
    public ResultVO<Object> save(@RequestBody PhaseVO vo) {
        phaseService.save(vo);
        return R.ok();
    }

    @PostMapping("/phase/update")
    public ResultVO<Object> update(@RequestBody PhaseVO vo) {
        phaseService.update(vo);
        return R.ok();
    }

    @GetMapping("/provs/all")
    public Map<String, Object> allProvs() {
        List<PhaseVO> sites = phaseService.findAllProvs();
        return R.okNamed("sites", sites);
    }

    @GetMapping("/citys/all/{provId}")
    public Map<String, Object> allCitys(@PathVariable Long provId) {
        List<PhaseVO> citys = phaseService.findCitysByProvId(provId);
        return R.okNamed("citys", citys);
    }
}
