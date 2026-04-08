package org.example.hjycommunity.community.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.hjycommunity.common.utils.OrikaUtils;
import org.example.hjycommunity.community.domain.dto.HjyCommunityDTO;
import org.example.hjycommunity.community.domain.pojo.HjyCommunity;
import org.example.hjycommunity.community.domain.vo.HjyCommunityVO;
import org.example.hjycommunity.community.mapper.HjyCommunityMapper;
import org.example.hjycommunity.community.service.HjyCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * HjyCommunityServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 18:46
 */

@Slf4j
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
    public List<HjyCommunityDTO> queryCommunityList(HjyCommunity hjyCommunity) {
        return hjyCommunityMapper.queryCommunityList(hjyCommunity);
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
     *
     * @param hjyCommunity
     * @return
     */
    @Override
    public Integer updateHjyCommunity(HjyCommunity hjyCommunity) {
        return hjyCommunityMapper.updateById(hjyCommunity);
    }

    /**
     * 删除小区信息
     *
     * @param hjyCommunityIds
     * @return
     */
    @Override
    public Integer deleteHjyCommunityByIds(List<Long> hjyCommunityIds) {
        return hjyCommunityMapper.deleteBatchIds(hjyCommunityIds);
    }

    /**
     * 获取小区下拉列表
     *
     * @param hjyCommunity
     * @return
     */
    @Override
    public List<HjyCommunityVO> queryPullDown(HjyCommunity hjyCommunity) {
        List<HjyCommunityDTO> hjyCommunityDTOS = hjyCommunityMapper.queryCommunityList(hjyCommunity);

        // 对象拷贝
        List<HjyCommunityVO> voList = hjyCommunityDTOS.stream().map(dto -> {
            // 使用 orika 完成对象拷贝
            return OrikaUtils.convert(dto, HjyCommunityVO.class);
        }).toList();
        log.info("log() returned with: voList => [{}]", voList);
        return voList;
    }
}
