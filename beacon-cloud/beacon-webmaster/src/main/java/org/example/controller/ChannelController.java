package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ChannelService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ChannelVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sys")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @GetMapping("/channel/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<ChannelVO> result = channelService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/channel/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        channelService.delete(ids);
        return R.ok();
    }

    @GetMapping("/channel/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        ChannelVO channel = channelService.findById(id);
        return R.okNamed("channel", channel);
    }

    @PostMapping("/channel/save")
    public ResultVO<Object> save(@RequestBody ChannelVO channelVO) {
        channelService.save(channelVO);
        return R.ok();
    }

    @PostMapping("/channel/update")
    public ResultVO<Object> update(@RequestBody ChannelVO channelVO) {
        channelService.update(channelVO);
        return R.ok();
    }

    @GetMapping("/channel/all")
    public Map<String, Object> all() {
        List<ChannelVO> list = channelService.findAll();
        return R.okNamed("channelsites", list);
    }
}
