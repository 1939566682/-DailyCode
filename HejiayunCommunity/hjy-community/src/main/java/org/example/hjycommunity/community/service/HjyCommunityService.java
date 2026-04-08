package org.example.hjycommunity.community.service;

import org.apache.ibatis.annotations.Param;
import org.example.hjycommunity.community.domain.dto.HjyCommunityDTO;
import org.example.hjycommunity.community.domain.pojo.HjyCommunity;
import org.example.hjycommunity.community.domain.vo.HjyCommunityVO;

import java.util.List;

/**
 * HjyCommunityService
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 18:46
 */

public interface HjyCommunityService {

    /**
     * 根据条件查询小区列表
     *
     * @param hjyCommunity
     * @return
     */
    List<HjyCommunityDTO> queryCommunityList(HjyCommunity hjyCommunity);

    /**
     * 新增小区
     *
     * @param hjyCommunity
     * @return
     */
    Integer insertHjyCommunity(HjyCommunity hjyCommunity);

    /**
     * 根据 ID 查询小区信息
     * @param hjyCommunityId
     * @return
     */
    HjyCommunity selectHjyCommunityById(Long hjyCommunityId);

    /**
     * 修改小区信息
     * @param hjyCommunity
     * @return
     */
    Integer updateHjyCommunity(HjyCommunity hjyCommunity);

    /**
     * 删除小区信息
     * @param hjyCommunityIds
     * @return
     */
    Integer deleteHjyCommunityByIds(@Param("hjyCommunityIds") List<Long> hjyCommunityIds);

    /**
     * 获取小区下拉列表
     * @param hjyCommunity
     * @return
     */
    List<HjyCommunityVO> queryPullDown(HjyCommunity hjyCommunity);
}
