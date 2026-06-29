package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ClientService;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ClientVO;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/client/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        PageResult<ClientVO> result = clientService.list(offset, limit, search);
        return R.ok(result.getTotal(), result.getRows());
    }

    @PostMapping("/client/del")
    public ResultVO<Object> del(@RequestBody Long[] ids) {
        clientService.delete(ids);
        return R.ok();
    }

    @GetMapping("/client/info/{id}")
    public Map<String, Object> info(@PathVariable Long id) {
        ClientVO client = clientService.findById(id);
        return R.okNamed("client", client);
    }

    @PostMapping("/client/save")
    public ResultVO<Object> save(@RequestBody ClientVO clientVO) {
        clientService.save(clientVO);
        return R.ok();
    }

    @PostMapping("/client/update")
    public ResultVO<Object> update(@RequestBody ClientVO clientVO) {
        clientService.update(clientVO);
        return R.ok();
    }
}
