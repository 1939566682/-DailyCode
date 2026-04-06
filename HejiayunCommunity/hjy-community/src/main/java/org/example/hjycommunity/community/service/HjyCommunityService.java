package org.example.hjycommunity.community.service;

import org.example.hjycommunity.community.domain.dto.HjyCommunityDTO;
import org.example.hjycommunity.community.domain.pojo.HjyCommunity;

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
     * @param hjyCommunity
     * @return
     */
    List<HjyCommunityDTO> QueryCommunityList(HjyCommunity hjyCommunity);

    /**
     * 新增小区
     * @param hjyCommunity
     * @return
     */
    Integer insertHjyCommunity(HjyCommunity hjyCommunity);
}
