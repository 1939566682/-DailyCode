package org.example.hjycommunity.web.controller.community;

import org.example.hjycommunity.common.core.controller.BaseController;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.core.page.PageResult;
import org.example.hjycommunity.community.domain.dto.HjyCommunityDTO;
import org.example.hjycommunity.community.domain.pojo.HjyCommunity;
import org.example.hjycommunity.community.service.HjyCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * HjyCommunityController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 18:48
 */

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
    public PageResult communityList(HjyCommunity hjyCommunity) {
        // 分页
        startPage();
        // 查询
        List<HjyCommunityDTO> hjyCommunityDTOS = hjyCommunityService.QueryCommunityList(hjyCommunity);
        // 封装
        return getPageResult(hjyCommunityDTOS);
    }


    /**
     * 新增小区
     * @param hjyCommunity
     * @return
     */
    @PostMapping
    public BaseResponse<Integer> addCommunity(@RequestBody HjyCommunity hjyCommunity) {
        return toAjax(hjyCommunityService.insertHjyCommunity(hjyCommunity));
    }

    /**
     * @URL http://localhost:8080/hejiayun/community/1623660256618201090
     * @请求方式 GET
     * @接口描述 根据小区 id 回显数据
     * @param communityId
     * @return
     */
    @GetMapping("/{communityId}")
    public BaseResponse<HjyCommunity> selectHjyCommunityById(@PathVariable Long communityId) {
        return BaseResponse.success(hjyCommunityService.selectHjyCommunityById(communityId));
    }

    /**
     * @URL http://localhost:8080/hejiayun/community
     * @请求方式 PUT
     * @接口描述 修改小区信息
     * @param hjyCommunity
     * @return
     */
    @PutMapping
    public BaseResponse<Integer> updateHjyCommunity(@RequestBody HjyCommunity hjyCommunity) {
        return toAjax(hjyCommunityService.updateHjyCommunity(hjyCommunity));
    }

    /**
     * @URL http://localhost:8080/hejiayun/community/1631558965133582338,1631559055512444929
     * @请求方式 DELETE
     * @接口描述 删除小区信息
     * @param hjyCommunityIds
     * @return
     */
    @DeleteMapping("/{hjyCommunityIds}")
    public BaseResponse<Integer> deleteHjyCommunityByIds(@PathVariable List<Long> hjyCommunityIds) {
        return toAjax(hjyCommunityService.deleteHjyCommunityByIds(hjyCommunityIds));
    }
}
