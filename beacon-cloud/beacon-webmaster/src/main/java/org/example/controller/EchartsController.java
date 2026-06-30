package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.vo.EchartsBarVO;
import org.example.vo.EchartsLineVO;
import org.example.vo.EchartsPieVO;
import org.example.vo.ResultVO;
import org.example.util.R;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class EchartsController {

    @GetMapping("/echarts/bar")
    public ResultVO<Object> bar() {
        EchartsBarVO vo = new EchartsBarVO();
        vo.setCategories(new String[]{"移动", "联通", "电信"});
        vo.setValues(new Long[]{100L, 80L, 60L});
        return R.ok(vo);
    }

    @GetMapping("/echarts/line")
    public ResultVO<Object> line() {
        EchartsLineVO vo = new EchartsLineVO();
        vo.setDates(new String[]{"1月", "2月", "3月", "4月", "5月", "6月"});
        vo.setSuccessCounts(new Long[]{120L, 200L, 150L, 80L, 70L, 110L});
        vo.setFailCounts(new Long[]{30L, 40L, 20L, 10L, 15L, 25L});
        return R.ok(vo);
    }

    /**
     * 前端 smspie.js 期望: r.legendData + r.seriesData
     * 返回格式: {code:0, msg:"success", legendData:[...], seriesData:[{name,value}...]}
     */
    @GetMapping("/echarts/pie")
    public Map<String, Object> pie() {
        List<String> legendData = Arrays.asList("成功", "失败");

        List<Map<String, Object>> seriesData = new ArrayList<>();
        Map<String, Object> item1 = new LinkedHashMap<>();
        item1.put("name", "成功");
        item1.put("value", 800L);
        seriesData.add(item1);
        Map<String, Object> item2 = new LinkedHashMap<>();
        item2.put("name", "失败");
        item2.put("value", 200L);
        seriesData.add(item2);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 0);
        result.put("msg", "success");
        result.put("legendData", legendData);
        result.put("seriesData", seriesData);
        return result;
    }
}
