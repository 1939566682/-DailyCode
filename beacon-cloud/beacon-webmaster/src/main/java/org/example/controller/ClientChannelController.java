package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ClientChannelService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ClientChannelVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class ClientChannelController {
    @Autowired
    private ClientChannelService clientChannelService;

    @GetMapping("/clientchannel/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<ClientChannelVO> result = clientChannelService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/clientchannel/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        clientChannelService.delete(ids);
        return R.ok();
    }

    @GetMapping("/clientchannel/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        ClientChannelVO clientChannel = clientChannelService.findById(id);
        return R.okNamed("clientchannel", clientChannel);
    }

    @PostMapping("/clientchannel/save")
    public ResultVO<Object> save(@RequestBody ClientChannelVO clientChannelVO) {
        clientChannelService.save(clientChannelVO);
        return R.ok();
    }

    @PostMapping("/clientchannel/update")
    public ResultVO<Object> update(@RequestBody ClientChannelVO clientChannelVO) {
        clientChannelService.update(clientChannelVO);
        return R.ok();
    }
}
