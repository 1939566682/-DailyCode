package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.util.R;
import org.example.vo.ResultVO;
import org.example.vo.SmsSendVO;
import org.springframework.web.bind.annotation.*;

/**
 * SmsSendController
 * 短信发送功能，对应前端 client/smssend.js
 * 路径: /sys/sms/save 和 /sys/sms/update
 *
 * 注意:
 * 1. 前端的 vm.sms 对象结构：{parentName, parentId, type, orderNum}
 * 2. 这可能是业务短信发送入口，而非系统管理功能
 * 3. 如果没有实际短信发送实现，save 返回成功即可（模拟）
 */
@Slf4j
@RestController
@RequestMapping("/sys")
public class SmsSendController {

    /**
     * 发送短信（新增）
     * 前端: smssend.js → POST /sys/sms/save → 期望 {code:0}
     * TODO: 需确认实际的短信发送逻辑，目前返回模拟成功
     */
    @PostMapping("/sms/save")
    public ResultVO<Object> save(@RequestBody SmsSendVO smsSendVO) {
        // TODO: 实现实际的短信发送逻辑
        // 目前模拟发送成功
        log.info("模拟发送短信（需完善实际逻辑）: {}", smsSendVO);
        return R.ok();
    }

    /**
     * 更新发送记录
     * 前端: smssend.js → POST /sys/sms/update → 期望 {code:0}
     * TODO: 需确认更新逻辑
     */
    @PostMapping("/sms/update")
    public ResultVO<Object> update(@RequestBody SmsSendVO smsSendVO) {
        // TODO: 实现实际的更新逻辑
        log.info("模拟更新短信发送记录（需完善实际逻辑）: {}", smsSendVO);
        return R.ok();
    }
}
