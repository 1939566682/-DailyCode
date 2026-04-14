package org.example.hjycommunity.web.controller.community;

import lombok.extern.slf4j.Slf4j;
import org.example.hjycommunity.common.core.controller.BaseController;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.core.page.PageResult;
import org.example.hjycommunity.community.domain.dto.HjyCommunityDTO;
import org.example.hjycommunity.community.domain.pojo.HjyCommunity;
import org.example.hjycommunity.community.domain.vo.HjyCommunityVO;
import org.example.hjycommunity.community.service.HjyCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * HjyCommunityController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 18:48
 */

@Slf4j
@RestController
@RequestMapping("/community")
public class HjyCommunityController extends BaseController {
    @Autowired
    private HjyCommunityService hjyCommunityService;


    /**
     * 根据条件查询小区列表
     *
     * @param hjyCommunity
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@pe.hasPermission('system:community:list')")
    public PageResult communityList(HjyCommunity hjyCommunity) {
        // 分页
        startPage();
        // 查询
        List<HjyCommunityDTO> hjyCommunityDTOS = hjyCommunityService.queryCommunityList(hjyCommunity);
        // 封装
        return getPageResult(hjyCommunityDTOS);
    }


    /**
     * 新增小区
     *
     * @param hjyCommunity
     * @return
     */
    @PostMapping
    public BaseResponse<Integer> addCommunity(@RequestBody HjyCommunity hjyCommunity) {
        return toAjax(hjyCommunityService.insertHjyCommunity(hjyCommunity));
    }

    /**
     * @param communityId
     * @return
     * @URL http://localhost:8080/hejiayun/community/1623660256618201090
     * @请求方式 GET
     * @接口描述 根据小区 id 回显数据
     */
    @GetMapping("/{communityId}")
    public BaseResponse<HjyCommunity> selectHjyCommunityById(@PathVariable Long communityId) {
        return BaseResponse.success(hjyCommunityService.selectHjyCommunityById(communityId));
    }

    /**
     * @param hjyCommunity
     * @return
     * @URL http://localhost:8080/hejiayun/community
     * @请求方式 PUT
     * @接口描述 修改小区信息
     */
    @PutMapping
    public BaseResponse<Integer> updateHjyCommunity(@RequestBody HjyCommunity hjyCommunity) {
        return toAjax(hjyCommunityService.updateHjyCommunity(hjyCommunity));
    }

    /**
     * @param hjyCommunityIds
     * @return
     * @URL http://localhost:8080/hejiayun/community/1631558965133582338,1631559055512444929
     * @请求方式 DELETE
     * @接口描述 删除小区信息
     */
    @DeleteMapping("/{hjyCommunityIds}")
    public BaseResponse<Integer> deleteHjyCommunityByIds(@PathVariable List<Long> hjyCommunityIds) {
        return toAjax(hjyCommunityService.deleteHjyCommunityByIds(hjyCommunityIds));
    }

    /**
     * 获取首页右上角的小区下拉列表
     *
     * @param hjyCommunity
     * @return
     */
    @GetMapping("/queryPullDown")
    public BaseResponse<List<HjyCommunityVO>> queryPullDown(HjyCommunity hjyCommunity) {

        log.info("queryPullDown");
        log.info("log() called with: hjyCommunity => [{}]", hjyCommunity);
        List<HjyCommunityVO> voList = null;
        try {
            voList = hjyCommunityService.queryPullDown(hjyCommunity);
        } catch (Exception e) {
            log.warn("获取小区下拉列表失败", e);
        }
        log.info("log() returned with: voList => [{}]", voList);
        return BaseResponse.success(voList);
    }
}
