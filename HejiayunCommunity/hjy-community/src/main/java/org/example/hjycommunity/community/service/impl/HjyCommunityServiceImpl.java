package org.example.hjycommunity.community.service.impl;

import org.example.hjycommunity.community.domain.dto.HjyCommunityDTO;
import org.example.hjycommunity.community.domain.pojo.HjyCommunity;
import org.example.hjycommunity.community.mapper.HjyCommunityMapper;
import org.example.hjycommunity.community.service.HjyCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HjyCommunityServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 18:46
 */

@Service
public class HjyCommunityServiceImpl implements HjyCommunityService {

    @Autowired
    private HjyCommunityMapper hjyCommunityMapper;

    private static final String CODE_PREFIX = "COMMUNITY_";

    /**
     * 根据条件查询小区列表
     *
     * @param hjyCommunity
     * @return
     */
    @Override
    public List<HjyCommunityDTO> QueryCommunityList(HjyCommunity hjyCommunity) {
        return hjyCommunityMapper.QueryCommunityList(hjyCommunity);
    }

    /**
     * 新增小区
     *
     * @param hjyCommunity
     * @return
     */
    @Override
    public Integer insertHjyCommunity(HjyCommunity hjyCommunity) {
        hjyCommunity.setCommunityCode(CODE_PREFIX + System.currentTimeMillis());
        return hjyCommunityMapper.insert(hjyCommunity);
    }

    /**
     * 根据 ID 查询小区信息
     *
     * @param hjyCommunityId
     * @return
     */
    @Override
    public HjyCommunity selectHjyCommunityById(Long hjyCommunityId) {
        return hjyCommunityMapper.selectById(hjyCommunityId);
    }

    /**
     * 修改小区信息
     * @param hjyCommunity
     * @return
     */
    @Override
    public Integer updateHjyCommunity(HjyCommunity hjyCommunity) {
        return hjyCommunityMapper.updateById(hjyCommunity);
    }

    /**
     * 删除小区信息
     * @param hjyCommunityIds
     * @return
     */
    @Override
    public Integer deleteHjyCommunityByIds(List<Long> hjyCommunityIds) {
        return hjyCommunityMapper.deleteBatchIds(hjyCommunityIds);
    }
}
