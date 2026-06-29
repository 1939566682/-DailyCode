package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.util.R;
import org.example.util.PageResult;
import org.example.vo.ResultVO;
import org.example.vo.ScheduleJobVO;
import org.example.vo.ScheduleLogVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ScheduleJobController
 * 路径前缀: /schedule/job 和 /schedule/log
 * 定时任务管理 + 任务日志，对应前端 schedule/job.js 和 schedule/log.js
 *
 * 注意:
 * 1. 项目未集成 xxl-job-core，因此返回模拟数据
 * 2. 如需对接 xxl-job，需先添加依赖并在配置中启用
 * 3. log 相关方法合并在此 Controller 中（都在 /schedule 路径下）
 */
@Slf4j
@RestController
@RequestMapping("/schedule")
public class ScheduleJobController {

    // ==================== 定时任务管理 ====================

    /**
     * 任务列表分页
     * 前端: job.js → GET /schedule/job/list → 期望 {total, rows}
     */
    @GetMapping("/job/list")
    public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) String search) {
        // TODO: 对接 xxl-job 后，调用 xxl-job-admin API 查询任务列表
        // 目前返回空列表
        log.info("查询定时任务列表（模拟数据，未集成xxl-job）");
        List<ScheduleJobVO> list = new ArrayList<>();
        return R.ok(0L, list);
    }

    /**
     * 删除任务
     * 前端: job.js → doTask('jobId', '删除', 'job/del') → POST /schedule/job/del
     */
    @PostMapping("/job/del")
    public ResultVO<Object> del(@RequestBody Long[] jobIds) {
        // TODO: 对接 xxl-job 后，调用删除接口
        log.info("删除定时任务（模拟）: jobIds={}", (Object) jobIds);
        return R.ok();
    }

    /**
     * 恢复任务
     * 前端: job.js → doTask('jobId', '恢复', 'job/resume') → POST /schedule/job/resume
     */
    @PostMapping("/job/resume")
    public ResultVO<Object> resume(@RequestBody Long[] jobIds) {
        log.info("恢复定时任务（模拟）: jobIds={}", (Object) jobIds);
        return R.ok();
    }

    /**
     * 暂停任务
     * 前端: job.js → doTask('jobId', '暂停', 'job/pause') → POST /schedule/job/pause
     */
    @PostMapping("/job/pause")
    public ResultVO<Object> pause(@RequestBody Long[] jobIds) {
        log.info("暂停定时任务（模拟）: jobIds={}", (Object) jobIds);
        return R.ok();
    }

    /**
     * 立即执行
     * 前端: job.js → doTask('jobId', '立即执行', 'job/run') → POST /schedule/job/run
     */
    @PostMapping("/job/run")
    public ResultVO<Object> run(@RequestBody Long[] jobIds) {
        log.info("立即执行定时任务（模拟）: jobIds={}", (Object) jobIds);
        return R.ok();
    }

    /**
     * 任务详情
     * 前端: job.js → GET /schedule/job/info/{jobId} → 期望 r.scheduleJob
     */
    @GetMapping("/job/info/{jobId}")
    public Map<String, Object> info(@PathVariable Long jobId) {
        // TODO: 对接 xxl-job 后，调用查询接口
        log.info("查询定时任务详情（模拟）: jobId={}", jobId);
        ScheduleJobVO job = new ScheduleJobVO();
        job.setJobId(jobId);
        job.setStatus(0);
        job.setCreateTime(new Date());
        return R.okNamed("scheduleJob", job);
    }

    /**
     * 新增任务
     * 前端: job.js → POST /schedule/job/save → 期望 {code:0}
     */
    @PostMapping("/job/save")
    public ResultVO<Object> save(@RequestBody ScheduleJobVO scheduleJobVO) {
        // TODO: 对接 xxl-job 后，调用新增接口
        log.info("新增定时任务（模拟）: {}", scheduleJobVO);
        return R.ok();
    }

    /**
     * 修改任务
     * 前端: job.js → POST /schedule/job/update → 期望 {code:0}
     */
    @PostMapping("/job/update")
    public ResultVO<Object> update(@RequestBody ScheduleJobVO scheduleJobVO) {
        // TODO: 对接 xxl-job 后，调用修改接口
        log.info("修改定时任务（模拟）: {}", scheduleJobVO);
        return R.ok();
    }

    // ==================== 任务日志 ====================

    /**
     * 日志列表分页
     * 前端: log.js → url: 'log/list'（需修复为 /schedule/log/list）
     * 期望 {total, rows}
     */
    @GetMapping("/log/list")
    public ResultVO<Object> logList(@RequestParam(defaultValue = "0") int offset,
                                     @RequestParam(defaultValue = "10") int limit,
                                     @RequestParam(required = false) String search) {
        // TODO: 对接 xxl-job 后，调用日志查询接口
        log.info("查询任务日志列表（模拟数据，未集成xxl-job）");
        List<ScheduleLogVO> list = new ArrayList<>();
        return R.ok(0L, list);
    }

    /**
     * 删除日志
     * 前端: log.js → doTask('logId', '删除', 'log/del') → 需修复为 POST /schedule/log/del
     */
    @PostMapping("/log/del")
    public ResultVO<Object> logDel(@RequestBody Long[] logIds) {
        // TODO: 对接 xxl-job 后，调用日志删除接口
        log.info("删除任务日志（模拟）: logIds={}", (Object) logIds);
        return R.ok();
    }
}
