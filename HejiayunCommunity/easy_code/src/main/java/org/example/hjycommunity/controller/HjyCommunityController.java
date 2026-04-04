package org.example.hjycommunity.controller;

import org.example.hjycommunity.entity.HjyCommunity;
import org.example.hjycommunity.service.HjyCommunityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 小区 (HjyCommunity)表控制层
 *
 * @author luoyu
 * @since 2026-04-03 19:19:54
 */
@RestController
@RequestMapping("hjyCommunity")
public class HjyCommunityController {
    /**
     * 服务对象
     */
    @Resource
    private HjyCommunityService hjyCommunityService;

    /**
     * 分页查询
     *
     * @param hjyCommunity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<HjyCommunity>> queryByPage(HjyCommunity hjyCommunity, PageRequest pageRequest) {
        return ResponseEntity.ok(this.hjyCommunityService.queryByPage(hjyCommunity, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HjyCommunity> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.hjyCommunityService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hjyCommunity 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HjyCommunity> add(HjyCommunity hjyCommunity) {
        return ResponseEntity.ok(this.hjyCommunityService.insert(hjyCommunity));
    }

    /**
     * 编辑数据
     *
     * @param hjyCommunity 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HjyCommunity> edit(HjyCommunity hjyCommunity) {
        return ResponseEntity.ok(this.hjyCommunityService.update(hjyCommunity));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.hjyCommunityService.deleteById(id));
    }

}

