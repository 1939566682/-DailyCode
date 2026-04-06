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
}
